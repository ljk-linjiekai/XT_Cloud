package com.xintu.manager.web.fegin;

import com.xintu.common.vo.DataGridResult;
import com.xt.manage.domain.model.Content;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;


@FeignClient(value = "xt-manager-service", path = "/content")
public interface ContentClient {

    @RequestMapping(value = "/queryContentListByPage", method = RequestMethod.GET)
    @ResponseBody
    public DataGridResult queryContentListByPage(@RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
                                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                 @RequestParam(value = "rows", defaultValue = "20") Integer rows) throws Exception;

    @RequestMapping(value = "/getPortalBigAdData", method = RequestMethod.GET)
    public String getPortalBigAdData() throws Exception;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveSelective(@RequestBody Content content) throws Exception;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateSelectiveById(@RequestBody Content content) throws Exception;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteByIds(@RequestBody Serializable[] ids) throws Exception;
}
