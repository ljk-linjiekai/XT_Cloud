package com.xintu.manager.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
@EnableHystrix
@EnableAutoConfiguration(exclude= { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
public class XtManagerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(XtManagerWebApplication.class, args);
    }

}
