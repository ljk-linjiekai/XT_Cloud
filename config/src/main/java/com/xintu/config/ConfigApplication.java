package com.xintu.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringCloudApplication
@EnableConfigServer
public class ConfigApplication {
	 private static Logger logger = LoggerFactory.getLogger(ConfigApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}

}
