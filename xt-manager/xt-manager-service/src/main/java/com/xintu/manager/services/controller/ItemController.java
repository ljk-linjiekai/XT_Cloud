package com.xintu.manager.services.controller;

import com.xintu.common.utils.JacksonMapper;
import com.xintu.common.vo.DataGridResult;
import com.xt.manage.api.interfaces.ItemService;
import com.xt.manage.domain.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @Autowired
    public ItemService itemService;

    @RequestMapping(value = "/saveItem", method = RequestMethod.POST)
    public void saveItem(@RequestBody Item item, @RequestParam(value = "desc", required = false) String desc) throws Exception {
        itemService.saveItem(item, desc);
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
    public void updateItem(@RequestBody Item item, @RequestParam(value = "desc", required = false) String desc) throws Exception {
        itemService.updateItem(item, desc);
        log.info("updateItem success:{}", JacksonMapper.toJson(item));
    }

    @RequestMapping(value = "/queryItemList", method = RequestMethod.GET)
    @ResponseBody
    public DataGridResult queryItemList(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "30") Integer rows) throws Exception {
        DataGridResult dataGridResult = itemService.queryItemList(title, page, rows);
        log.info("queryItemList success:{}", JacksonMapper.toJson(dataGridResult));
        return dataGridResult;
    }

    @RequestMapping(value = "/updateItemStatuByIds", method = RequestMethod.POST)
    public void updateItemStatuByIds(@RequestParam("ids") Long[] ids, @RequestParam(value = "statu", defaultValue = "3") Integer statu) {
        itemService.updateItemStatuByIds(ids, statu);
        log.info("updateItemStatuByIds success:{}", ids.toString());
    }

}
