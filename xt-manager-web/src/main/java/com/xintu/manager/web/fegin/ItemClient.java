package com.xintu.manager.web.fegin;

import com.xintu.common.utils.JacksonMapper;
import com.xintu.common.vo.DataGridResult;
import com.xt.manage.domain.model.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "xt-manager-service", path = "/item")
public interface ItemClient {


    @RequestMapping(value = "/saveItem", method = RequestMethod.POST)
    public void saveItem(@RequestBody Item item, @RequestParam(value = "desc", required = false) String desc) throws Exception;

    @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
    public void updateItem(@RequestBody Item item, @RequestParam(value = "desc", required = false) String desc) throws Exception;


    @RequestMapping(value = "/queryItemList", method = RequestMethod.GET)
    public @ResponseBody DataGridResult queryItemList(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "30") Integer rows) throws Exception;

    @RequestMapping(value = "/updateItemStatuByIds", method = RequestMethod.POST)
    public void updateItemStatuByIds(@RequestParam("ids") Long[] ids, @RequestParam(value = "statu", defaultValue = "3") Integer statu);

    @RequestMapping(value = "/saveSelective", method = RequestMethod.POST)
    public void saveSelective(@RequestBody  Item item) throws Exception;

    @RequestMapping(value = "/queryById/{id}", method = RequestMethod.GET)
    public @ResponseBody  Item queryById(@PathVariable("id") Long id) throws Exception;

    @RequestMapping(value = "/updateSelectiveById", method = RequestMethod.POST)
    public void updateSelectiveById(@RequestBody  Item item) throws Exception;

    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
    public void deleteByIds(@RequestBody Long[] ids) throws Exception;
}
