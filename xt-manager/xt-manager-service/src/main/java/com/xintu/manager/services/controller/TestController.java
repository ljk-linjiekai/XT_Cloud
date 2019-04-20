package com.xintu.manager.services.controller;

import com.xt.manage.api.interfaces.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    public TestService testService;


    @RequestMapping("/date")
    @ResponseBody
    public String queryCurrentDate(){
        String s = testService.queryCurrentDate();
        log.info("queryCurrentDate success:{}",s);
        return s;
    }
}
