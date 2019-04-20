package com.xintu.manager.services.controller;

import com.xintu.common.utils.JacksonMapper;
import com.xintu.common.vo.DataGridResult;
import com.xt.manage.api.interfaces.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        //查询分页列表
        DataGridResult dataGridResult = contentService.queryContentListByPage(categoryId, page, rows);
        log.info("queryContentListByPage success:{}", JacksonMapper.toJson(dataGridResult));
        return dataGridResult;
    }

    @RequestMapping(value = "/getPortalBigAdData", method = RequestMethod.GET)
    public String getPortalBigAdData() throws Exception {
        String portalBigAdData = contentService.getPortalBigAdData();
        log.info("getPortalBigAdData success:{}", JacksonMapper.toJson(portalBigAdData));
        return portalBigAdData;
    }
}
