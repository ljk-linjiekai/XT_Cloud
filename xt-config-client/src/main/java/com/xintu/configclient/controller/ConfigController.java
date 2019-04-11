package com.xintu.configclient.controller;

import com.xintu.configclient.configModels.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Autowired
    private CommonConfig config;

    @Value("${profile}")
    private String username;
    /**
     * 返回配置文件中的值
     */
    @GetMapping("/profile")
    @ResponseBody
    public String returnFormValue() {
        return username;
    }
}
