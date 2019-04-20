package com.xintu.manager.services.controller;

import com.xintu.common.utils.JacksonMapper;
import com.xintu.common.vo.DataGridResult;
import com.xintu.manager.services.impl.ItemDescServiceImpl;
import com.xt.manage.api.interfaces.ItemDescService;
import com.xt.manage.api.interfaces.ItemService;
import com.xt.manage.model.ItemDesc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/item/desc")
public class ItemDescController {
    @Autowired
    public ItemDescService itemDescService;

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ItemDesc queryItemDescByItemid(@PathVariable(value = "itemId") Long itemId) throws Exception {
        ItemDesc itemDesc = itemDescService.queryById(itemId);
        log.info("queryItemDescByItemid success:{}", JacksonMapper.toJson(itemDesc));
        return itemDesc;
    }

}
