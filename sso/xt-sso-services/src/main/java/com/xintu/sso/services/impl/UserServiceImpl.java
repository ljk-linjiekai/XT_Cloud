package com.xintu.sso.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xintu.common.utils.JacksonMapper;
import com.xintu.common.utils.NumberId;
import com.xintu.sso.api.mapper.UserMapper;
import com.xintu.sso.api.service.UserService;
import com.xintu.sso.domain.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.Date;
import java.util.List;

/**
 * @author 林捷凯
 * @version 1.0
 * @Time：2017年2月22日 下午2:58:53
 */
@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JedisCluster jedisCluster;

    //redis中的key
    private final static String REDIS_KEY_PREFIX = "TT_TICKET_";

    private static final ObjectMapper Mapper = new ObjectMapper();

    @Override
    public Boolean check(String param, Integer type) throws Exception {
        //查询数据库中是否存在param数据；如果存在说明不可以用，否则说明可用
        User user = new User();
        switch (type) {
            case 1:
                user.setUsername(param);
                break;
            case 2:
                user.setPhone(param);
                break;

            default:
                user.setEmail(param);
                break;
        }

        long s1 = System.currentTimeMillis();
        //查询数据记录
        int count = userMapper.selectCount(new QueryWrapper<User>().setEntity(user));
        logger.info("查询数据库记录::{},costTime::{}", count, System.currentTimeMillis() - s1);
        return count == 0;
    }


    @Override
    public String queryUserStrByTicket(String ticket) {
        String key = REDIS_KEY_PREFIX + ticket;
        //1、根据ticket到redis中查询用户json格式字符串
        String userJsonStr = jedisCluster.get(key);
        if (StringUtils.isNotBlank(userJsonStr)) {
            //2、既然会查询用户信息，说明用户处于活跃状态
            //需要重置redis ticket的生存时间；生存时间1小时
            jedisCluster.expire(userJsonStr, 3600);
        }


        return userJsonStr;
    }


    @Override
    public void save(User user) throws Exception {
        if (user.getId() == null || user.getId() == 0) {
            user.setId(NumberId.getNumberId());
        }

        //设置创建时间和更新时间
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        //设置密码
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        jedisCluster.set(user.getId() + "", JacksonMapper.toJson(user));
        jedisCluster.expire(user.getId() + "", 60);
        //保存注册的用户
        userMapper.insert(user);
    }


    @Override
    public String login(String username, String password) throws Exception {

        //1.到数据库中查询用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(DigestUtils.md5Hex(password));
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.setEntity(user);
        List<User> list = userMapper.selectList(userQueryWrapper);
        if (list != null && list.size() > 0) {
            User userTemp = list.get(0);
            //2.设置用户的ticket,需要在数据库中唯一：用户id+时间戳
            String ticket = userTemp.getId() + System.currentTimeMillis() + "";
            ticket = DigestUtils.md5Hex(ticket);

            String key = REDIS_KEY_PREFIX + ticket;
            logger.info("用户{} 登录ticket：{}",userTemp.getId(),key);
            //将用户信息设置到redis中
            jedisCluster.setex(key, 3600, Mapper.writeValueAsString(userTemp));
            return ticket;

        }
        return null;
    }


    @Override
    public User queryUserByTicket(String ticket) throws Exception {
        String key = REDIS_KEY_PREFIX + ticket;
        //1、根据ticket到redis中查询用户json格式字符串
        String userJsonStr = jedisCluster.get(key);
        if (StringUtils.isNotBlank(userJsonStr)) {
            //2、既然会查询用户信息，说明用户处于活跃状态
            //需要重置redis ticket的生存时间；生存时间1小时
            jedisCluster.expire(key, 3600);

            return Mapper.readValue(userJsonStr, User.class);
        }

        return null;


    }
}
