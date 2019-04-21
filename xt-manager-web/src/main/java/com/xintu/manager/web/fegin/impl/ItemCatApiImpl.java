package com.xintu.manager.web.fegin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.xintu.common.utils.JacksonMapper;
import com.xintu.manager.web.fegin.ContentClient;
import com.xintu.manager.web.fegin.ItemCatApi;
import com.xintu.manager.web.fegin.ItemCatClient;
import com.xt.manage.api.interfaces.ItemCatService;
import com.xt.manage.api.mapper.ItemCatMapper;
import com.xt.manage.domain.model.ItemCat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


@Service
@Slf4j
public class ItemCatApiImpl implements ItemCatApi {

    @Autowired
    public ItemCatClient itemCatClient;

    @Override
    public List<ItemCat> queryItemCatByPage(Integer page, Integer rows)  throws Exception{
        return itemCatClient.queryItemCatByPage(page,rows);
    }

    @Override
    public ItemCat queryById(Long id)throws Exception {
        return itemCatClient.queryById(id);
    }

    @Override
    public List<ItemCat> queryListByWhere(ItemCat itemCat)throws Exception {
        return itemCatClient.queryListByWhere(itemCat);
    }
}
