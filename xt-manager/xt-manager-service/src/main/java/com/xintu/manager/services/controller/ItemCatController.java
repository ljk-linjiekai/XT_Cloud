package com.xintu.manager.services.controller;

import com.xintu.common.utils.JacksonMapper;
import com.xt.manage.api.interfaces.ItemCatService;
import com.xt.manage.domain.model.ItemCat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item/cat")
@Slf4j
public class ItemCatController {

    @Autowired
    public ItemCatService itemCatService;

    @RequestMapping(value = "/query/{page}", method = RequestMethod.GET)
    @ResponseBody
    public List<ItemCat> queryItemCatByPage(@PathVariable("page") Integer page,
                                            @RequestParam(value = "rows", defaultValue = "5") Integer rows) throws Exception {
        return  itemCatService.queryItemCatByPage(page, rows);
    }

    @RequestMapping(value = "/queryById/{id}", method = RequestMethod.GET)
    public @ResponseBody ItemCat queryById(@PathVariable("id") Long id) throws Exception{
        return itemCatService.queryById(id);
    }

    @RequestMapping(value = "/queryListByWhere", method = RequestMethod.POST)
    public @ResponseBody List<ItemCat> queryListByWhere(@RequestBody ItemCat itemCat) throws Exception{
        return itemCatService.queryListByWhere(itemCat);
    }
}
