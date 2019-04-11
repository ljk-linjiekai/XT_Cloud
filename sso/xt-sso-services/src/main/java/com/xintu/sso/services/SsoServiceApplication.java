package com.xintu.sso.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
//@ImportResource(locations = {"classpath*:spring/applicationContext-trans.xml" })
@ServletComponentScan
@ComponentScan(basePackages = {"com.xintu.*" })
@EnableConfigurationProperties
public class SsoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsoServiceApplication.class, args);
	}
	
	
}
