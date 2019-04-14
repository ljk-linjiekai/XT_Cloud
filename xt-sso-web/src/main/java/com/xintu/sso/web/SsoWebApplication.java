package com.xintu.sso.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 * @author 林捷凯
 * @Time：2018年9月6日 下午8:39:13
 * @version 1.0
 * @Function: EnableHystrix:熔断机制，EnableFeignClients：服务之间调用，RibbonClients:服务之间调用时负载均衡策略
 */
@SpringCloudApplication
@EnableFeignClients
@EnableHystrix
@EnableAutoConfiguration(exclude= { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
public class SsoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoWebApplication.class, args);
	}
}
