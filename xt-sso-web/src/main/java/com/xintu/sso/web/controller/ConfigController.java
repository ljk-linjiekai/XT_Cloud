package com.xintu.sso.web.controller;

import com.xintu.sso.web.feign.ConfigServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Autowired
    @Qualifier("configServiceClientImpl")
    public ConfigServiceClient client;

    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public  String check(){
        return  client.check();
    }

}
