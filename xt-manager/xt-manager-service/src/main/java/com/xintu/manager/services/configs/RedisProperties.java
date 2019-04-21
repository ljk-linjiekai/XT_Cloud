package com.xintu.manager.services.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Data
public class RedisProperties {

    private String nodes;
    private Integer commandTimeout;
}