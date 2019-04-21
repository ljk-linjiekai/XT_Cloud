package com.xintu.manager.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xintu.common.utils.JacksonMapper;
import com.xintu.common.utils.StringUtils;
import com.xintu.common.vo.DataGridResult;
import com.xt.manage.api.interfaces.ContentService;
import com.xt.manage.api.mapper.ContentMapper;
import com.xt.manage.domain.model.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private JedisCluster jedisCluster;

    @Value("${CONTENT_CATEGORY_BIG_AD_ID}")
    private Long CONTENT_CATEGORY_BIG_AD_ID;
    @Value("${PORTAL_INDEX_BIG_AD_NUMBER}")
    private Integer PORTAL_INDEX_BIG_AD_NUMBER;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String REDIS_AD_KEY = "XTClOUD_INDEX_BIG_AD_DATA";
    private static final int REDIS_AD_EXPIRE_TIME = 60 * 60 * 24;


    /**
     * ① 先从缓存中命中；
     * ② 没有命中执行原有逻辑，从MySQL数据库中查询；
     * ③ 把数据保存在redis中
     * 不能够影响正常的业务逻辑，可以对缓存的代码进行try/catch
     */
    @Override
    public DataGridResult queryContentListByPage(Long categoryId, Integer page, Integer rows) {
        try {
            //设置根据更新时间降序排序
            Content content = new Content();
            content.setCategoryId(categoryId);
            //设置分页
            PageHelper.startPage(page, rows);
            //查询
            List<Content> list = contentMapper.selectList(new QueryWrapper<Content>().setEntity(content));
            PageInfo<Content> pageInfo = new PageInfo<>(list);
            DataGridResult dataGridResult = new DataGridResult(pageInfo.getTotal(), pageInfo.getList());
            log.info("分页 查询数据库类目，success：{}", JacksonMapper.toJson(dataGridResult));
            return dataGridResult;
        } catch (Exception e) {
            log.error("分页 查询数据库类目，error：{}", e);
        }
        return null;
    }

    @Override
    public String getPortalBigAdData() throws Exception {
        try {
            // 先重redis缓存中查询数据
            String jsonStr = jedisCluster.get(REDIS_AD_KEY);
            if (StringUtils.isNotBlankOrNull(jsonStr)) {
                log.info("查询redis缓存大广告success：{}", jsonStr);
                return jsonStr;
            }
        } catch (Exception e1) {
            log.error("查询redis缓存大广告error：{}", e1);
        }

        //1.获取n条大广告数据
        DataGridResult dataGridResult = queryContentListByPage(CONTENT_CATEGORY_BIG_AD_ID, 1,
                PORTAL_INDEX_BIG_AD_NUMBER);
        //2.转换数据格式
        List<Content> list = (List<Content>) dataGridResult.getRows();
        if (!CollectionUtils.isEmpty(list)) {
            List<Map<String, Object>> resultList = new ArrayList<>();

            //3.转换列表数据为字符串
            list.forEach(c -> {
                final Map<String, Object> map = new HashMap<>();
                map.put("alt", c.getTitle());
                map.put("height", 240);
                map.put("heightB", 240);
                map.put("href", c.getUrl());
                map.put("src", c.getPic());
                map.put("srcB", c.getPic());
                map.put("width", 670);
                map.put("widthB", 550);
                //添加map到集合中
                resultList.add(map);
            });

            //4.转换成字符串返回
            String str = MAPPER.writeValueAsString(resultList);
            try {
                // 将数据写入到redis缓存中；大广告数据设置1天的生存时间
                jedisCluster.setex(REDIS_AD_KEY, REDIS_AD_EXPIRE_TIME, str);
                log.info("大广告数据缓存redis 24小时 ,key：{},value：{}", REDIS_AD_KEY, str);
            } catch (Exception e) {
                log.error("大广告数据缓存redis error：{}", e);
            }
            return str;
        }
        return null;
    }


}
