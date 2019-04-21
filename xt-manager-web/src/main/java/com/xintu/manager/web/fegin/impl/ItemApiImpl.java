package com.xintu.manager.web.fegin.impl;

import com.xintu.common.vo.DataGridResult;
import com.xintu.manager.web.fegin.ItemApi;
import com.xintu.manager.web.fegin.ItemClient;
import com.xt.manage.domain.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ItemApiImpl implements ItemApi {

    @Autowired
    public ItemClient itemClient;

    @Override
    public void saveItem(Item item, String desc) throws Exception {
        itemClient.saveItem(item, desc);
    }

    @Override
    public void updateItem(Item item, String desc) throws Exception {
        itemClient.updateItem(item, desc);
    }

    @Override
    public DataGridResult queryItemList(String title, Integer page, Integer rows) throws Exception {
        return itemClient.queryItemList(title, page, rows);
    }

    @Override
    public void updateItemStatuByIds(Long[] ids, Integer statu) throws Exception {
        itemClient.updateItemStatuByIds(ids, statu);
    }

    @Override
    public void saveSelective(Item item) throws Exception {
        itemClient.saveSelective(item);
    }

    @Override
    public Item queryById(Long id) throws Exception {
        return itemClient.queryById(id);
    }

    @Override
    public void updateSelectiveById(Item item) throws Exception {
        itemClient.updateSelectiveById(item);
    }

    @Override
    public void deleteByIds(Long[] ids) throws Exception {
        itemClient.deleteByIds(ids);
    }
}
