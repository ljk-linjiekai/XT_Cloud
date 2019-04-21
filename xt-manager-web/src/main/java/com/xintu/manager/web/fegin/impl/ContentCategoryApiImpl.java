package com.xintu.manager.web.fegin.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xintu.manager.web.fegin.ContentCategoryApi;
import com.xintu.manager.web.fegin.ContentCategoryClient;
import com.xt.manage.api.interfaces.ContentCategoryService;
import com.xt.manage.api.mapper.ContentCategoryMapper;
import com.xt.manage.domain.model.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContentCategoryApiImpl implements ContentCategoryApi {

    @Autowired
    ContentCategoryClient client;

    @Override
    public ContentCategory saveContentCategory(ContentCategory contentCategory) throws Exception {
        return client.saveContentCategory(contentCategory);
    }

    @Override
    public void deleteContentCategory(ContentCategory contentCategory) throws Exception {
        client.deleteContentCategory(contentCategory);
    }

    @Override
    public void updateSelectiveById(ContentCategory contentCategory) throws Exception {
        client.updateSelectiveById(contentCategory);
    }

    @Override
    public List<ContentCategory> queryListByWhere(ContentCategory contentCategory) throws Exception {
        return client.queryContentCategoryListByParentId(contentCategory);
    }
}
