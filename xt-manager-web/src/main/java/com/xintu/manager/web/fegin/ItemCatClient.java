package com.xintu.manager.web.fegin;

import com.xintu.common.utils.JacksonMapper;
import com.xt.manage.domain.model.ItemCat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


@FeignClient(value = "xt-manager-service", path = "/item/cat")
public interface ItemCatClient {


    @RequestMapping(value = "/query/{page}", method = RequestMethod.GET)
    public @ResponseBody List<ItemCat> queryItemCatByPage(@PathVariable("page") Integer page,
                                            @RequestParam(value = "rows", defaultValue = "5") Integer rows) throws Exception;

    @RequestMapping(value = "/queryItemCatListByParentId", method = RequestMethod.POST)
    public @ResponseBody List<ItemCat> queryItemCatListByParentId(@RequestBody ItemCat itemCat) throws Exception;

    @RequestMapping(value = "/queryById/{id}", method = RequestMethod.GET)
    public @ResponseBody ItemCat queryById(@PathVariable("id") Long id) throws Exception;

    @RequestMapping(value = "/queryListByWhere", method = RequestMethod.POST)
    public @ResponseBody List<ItemCat> queryListByWhere(@RequestBody ItemCat itemCat) throws Exception;
}
