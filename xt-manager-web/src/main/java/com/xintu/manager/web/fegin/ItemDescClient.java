package com.xintu.manager.web.fegin;

import com.xt.manage.domain.model.ItemDesc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@FeignClient(value = "xt-manager-service", path = "/item/desc")
public interface ItemDescClient {

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public @ResponseBody
    ItemDesc queryItemDescByItemid(@PathVariable(value = "itemId") Long itemId) throws Exception;
}
