package com.xintu.manager.services.controller;

import com.xintu.common.utils.JacksonMapper;
import com.xintu.common.vo.DataGridResult;
import com.xt.manage.api.interfaces.ContentService;
import com.xt.manage.domain.model.Content;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/content")
@Slf4j
public class ContentController {

    @Autowired
    public ContentService contentService;

    @RequestMapping(value = "/queryContentListByPage", method = RequestMethod.GET)
    @ResponseBody
    public DataGridResult queryContentListByPage(@RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
                                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                 @RequestParam(value = "rows", defaultValue = "20") Integer rows) throws Exception {
        return contentService.queryContentListByPage(categoryId, page, rows);
    }

    @RequestMapping(value = "/getPortalBigAdData", method = RequestMethod.GET)
    public String getPortalBigAdData() throws Exception {
        return contentService.getPortalBigAdData();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveSelective(@RequestBody Content content) throws Exception{
        contentService.saveSelective(content);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateSelectiveById(@RequestBody Content content) throws Exception{
        contentService.updateSelectiveById(content);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteByIds(@RequestBody Serializable[] ids) throws Exception{
        contentService.deleteByIds(ids);
    }
}
