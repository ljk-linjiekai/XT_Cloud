package com.xintu.sso.web.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "xt-cloud-config")
public interface ConfigServiceApi {
    @RequestMapping(value = "/check",method= RequestMethod.GET)
    public String check();
}
