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
        List<ItemCat> itemCats = itemCatService.queryItemCatByPage(page, rows);
        log.info("queryItemCatByPage success:{}", JacksonMapper.toJson(itemCats));
        return itemCats;
    }

    @RequestMapping(value = "/queryItemCatListByParentId", method = RequestMethod.POST)
    @ResponseBody
    public List<ItemCat> queryItemCatListByParentId(@RequestBody ItemCat itemCat) throws Exception {

        //执行查询
        List<ItemCat> list = itemCatService.queryListByWhere(itemCat);
        log.info("queryItemCatListByParentId success:{}", JacksonMapper.toJson(list));
        //返回查询列表
        return list;
    }
}
