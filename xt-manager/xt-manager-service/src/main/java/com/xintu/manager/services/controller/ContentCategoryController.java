package com.xintu.manager.services.controller;


import com.xintu.common.utils.JacksonMapper;
import com.xt.manage.api.interfaces.ContentCategoryService;
import com.xt.manage.domain.model.ContentCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/category")
@Slf4j
public class ContentCategoryController {
    @Autowired
    public ContentCategoryService contentCategoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ContentCategory saveContentCategory(@RequestBody ContentCategory contentCategory) throws Exception {
        return contentCategoryService.saveContentCategory(contentCategory);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteContentCategory(@RequestBody ContentCategory contentCategory) throws Exception {
        //删除该节点以及子孙节点
        contentCategoryService.deleteContentCategory(contentCategory);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateSelectiveById(@RequestBody ContentCategory contentCategory) throws Exception {
        contentCategoryService.updateSelectiveById(contentCategory);
    }


    @RequestMapping(value = "/queryContentCategoryListByParentId", method = RequestMethod.GET)
    @ResponseBody
    public List<ContentCategory> queryContentCategoryListByParentId(@RequestBody ContentCategory contentCategory) throws Exception {
        return contentCategoryService.queryListByWhere(contentCategory);
    }


}
