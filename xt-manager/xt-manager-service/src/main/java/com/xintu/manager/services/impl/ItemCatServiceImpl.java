package com.xintu.manager.services.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.xintu.common.utils.JacksonMapper;
import com.xt.manage.api.interfaces.ItemCatService;
import com.xt.manage.api.mapper.ItemCatMapper;
import com.xt.manage.domain.model.ItemCat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> queryItemCatByPage(Integer page, Integer rows) {
        try {
            //设置分页
            PageHelper.startPage(page, rows);
            //查询数据
            List<ItemCat> list = itemCatMapper.selectList(new QueryWrapper<ItemCat>());
            log.info("queryItemCatByPage success:{}", JacksonMapper.toJson(list));
            return list;
        } catch (Exception e) {
            log.error("queryItemCatByPage error:{}", e);
        }
        return null;
    }

}
