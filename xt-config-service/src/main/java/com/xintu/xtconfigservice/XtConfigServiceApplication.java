package com.xintu.xtconfigservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringCloudApplication
@EnableConfigServer
public class XtConfigServiceApplication {
    private static Logger logger = LoggerFactory.getLogger(XtConfigServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(XtConfigServiceApplication.class, args);
    }
}
