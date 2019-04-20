package com.xintu.manager.services.controller;


import com.xintu.common.utils.JacksonMapper;
import com.xt.manage.api.interfaces.ContentCategoryService;
import com.xt.manage.model.ContentCategory;
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
        ContentCategory contentCategory1 = contentCategoryService.saveContentCategory(contentCategory);
        log.info("saveContentCategory success:{}", JacksonMapper.toJson(contentCategory1));
        return contentCategory1;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteContentCategory(@RequestBody ContentCategory contentCategory) throws Exception {
        //删除该节点以及子孙节点
        contentCategoryService.deleteContentCategory(contentCategory);
        log.info("{}:deleteContentCategory success:{}", contentCategory.getId());

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateSelectiveById(@RequestBody ContentCategory contentCategory) throws Exception {
        contentCategoryService.updateSelectiveById(contentCategory);
        log.info("{}:updateSelectiveById success", contentCategory.getId());
    }


    @RequestMapping(value = "/queryContentCategoryListByParentId", method = RequestMethod.GET)
    @ResponseBody
    public List<ContentCategory> queryContentCategoryListByParentId(@RequestBody ContentCategory contentCategory) throws Exception {
        List<ContentCategory> contentCategories = contentCategoryService.queryListByWhere(contentCategory);
        log.info("queryContentCategoryListByParentId success:{}", JacksonMapper.toJson(contentCategories));
        return contentCategories;
    }


}
