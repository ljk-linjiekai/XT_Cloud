package com.xintu.manager.web.fegin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xintu.common.utils.JacksonMapper;
import com.xintu.common.utils.StringUtils;
import com.xintu.common.vo.DataGridResult;
import com.xintu.manager.web.fegin.ContentApi;
import com.xintu.manager.web.fegin.ContentClient;
import com.xt.manage.api.interfaces.ContentService;
import com.xt.manage.api.mapper.ContentMapper;
import com.xt.manage.domain.model.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.JedisCluster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ContentApiImpl implements ContentApi {

    @Autowired
    public ContentClient contentClient;

    @Override
    public DataGridResult queryContentListByPage(Long categoryId, Integer page, Integer rows) throws  Exception{
        return contentClient.queryContentListByPage(categoryId,page,rows);
    }

    @Override
    public String getPortalBigAdData() throws Exception {
        return contentClient.getPortalBigAdData();
    }

    @Override
    public void saveSelective(Content content) throws Exception {
        contentClient.saveSelective(content);
    }

    @Override
    public void updateSelectiveById(Content content) throws Exception {
        contentClient.updateSelectiveById(content);
    }

    @Override
    public void deleteByIds(Serializable[] ids) throws Exception {
        contentClient.deleteByIds(ids);
    }
}
