package com.xintu.manager.web.fegin.impl;

import com.xintu.common.vo.DataGridResult;
import com.xintu.manager.web.fegin.ContentApi;
import com.xintu.manager.web.fegin.ContentClient;
import com.xt.manage.domain.model.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

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
