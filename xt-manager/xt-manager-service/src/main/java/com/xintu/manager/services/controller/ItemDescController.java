package com.xintu.manager.services.controller;

import com.xintu.common.utils.JacksonMapper;
import com.xt.manage.api.interfaces.ItemDescService;
import com.xt.manage.domain.model.ItemDesc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/item/desc")
public class ItemDescController {
    @Autowired
    public ItemDescService itemDescService;

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ItemDesc queryItemDescByItemid(@PathVariable(value = "itemId") Long itemId) throws Exception {
        return itemDescService.queryById(itemId);
    }

}
