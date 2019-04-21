package com.xintu.manager.web.fegin;

import com.xintu.common.utils.JacksonMapper;
import com.xt.manage.domain.model.ContentCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FeignClient(value = "xt-manager-service",path = "/content/category")
public interface ContentCategoryClient {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ContentCategory saveContentCategory(@RequestBody ContentCategory contentCategory) throws Exception ;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteContentCategory(@RequestBody ContentCategory contentCategory) throws Exception ;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateSelectiveById(@RequestBody ContentCategory contentCategory) throws Exception ;

    @RequestMapping(value = "/queryContentCategoryListByParentId", method = RequestMethod.GET)
    @ResponseBody
    public List<ContentCategory> queryContentCategoryListByParentId(@RequestBody ContentCategory contentCategory) throws Exception ;

}
