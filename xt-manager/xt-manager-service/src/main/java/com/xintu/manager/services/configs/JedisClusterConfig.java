package com.xintu.manager.services.configs;

import com.xintu.common.utils.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description(描述): 
 * @auther: Jack Lin
 * @param :
 * @return : 
 * @date: 2019/4/14 21:32
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
        if(StringUtils.isNotBlankOrNull(redisProperties.getPassword())){
            return new JedisCluster(nodes,redisProperties.getCommandTimeout(),redisProperties.getSoTimeout(),redisProperties.getMaxAttempts(),redisProperties.getPassword(),getPoolConfig());
        }
        return new JedisCluster(nodes, redisProperties.getCommandTimeout());
    }

    /**
     *
     * @Title: getPoolConfig
     * @Description: 连接池的配置与horo一致
     */
    public JedisPoolConfig getPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);
        config.setMaxIdle(50);
        config.setMinIdle(10);
        config.setMaxWaitMillis(15000);
        config.setLifo(true);
        config.setBlockWhenExhausted(true);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        config.setTestWhileIdle(true);
        config.setTimeBetweenEvictionRunsMillis(30000);
        return config;
    }
}
