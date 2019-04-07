package com.xintu.sso.api.service;

import com.xintu.sso.domain.model.User;

/**
 * @author 林捷凯
 * @version 1.0
 * @Time：2017年2月22日 下午2:53:23
 */
public interface UserService {

    /**
     * 判断用户名是否可用
     * check:().
     *
     * @param param
     * @param type
     * @return
     * @author 林捷凯
     * @Time：2017年3月1日 上午1:01:34
     */
    Boolean check(String param, Integer type) throws Exception;


    /**
     * 根据ticket查询redis中是否有用户信息
     * queryUserByTicket:().
     *
     * @param ticket
     * @return
     * @author 林捷凯
     * @Time：2017年3月1日 上午1:02:03
     */
    String queryUserStrByTicket(String ticket) throws Exception;

    /**
     * 注册用户
     * save:().
     *
     * @param user
     * @author 林捷凯
     * @Time：2017年3月1日 上午1:01:22
     */
    void save(User user) throws Exception;

    /**
     * 用户登录
     * login:().
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     * @author 林捷凯
     * @Time：2017年3月1日 上午1:01:56
     */
    String login(String username, String password) throws Exception;

    /**
     * 根据ticket到redis里面查询用户信息
     *
     * @param ticket 登录凭证信息
     * @return
     * @throws Exception
     */
    User queryUserByTicket(String ticket) throws Exception;

}
