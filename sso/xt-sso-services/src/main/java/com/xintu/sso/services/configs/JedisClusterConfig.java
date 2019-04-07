package com.xintu.sso.services.configs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaxuan on 16/11/8.
 * 获取JedisCluster的配置
 */
@Configuration
@ConditionalOnClass({JedisCluster.class})
@EnableConfigurationProperties(RedisProperties.class)
public class JedisClusterConfig {

    @Inject
    private RedisProperties redisProperties;

    @Bean
    @Singleton
    public JedisCluster getJedisCluster() {
        String[] serverArray = redisProperties.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String ipPort: serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes, redisProperties.getCommandTimeout());
    }
}
