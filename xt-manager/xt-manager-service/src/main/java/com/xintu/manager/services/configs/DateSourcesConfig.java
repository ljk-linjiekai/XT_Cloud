package com.xintu.manager.services.configs;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 
 * @ClassName: DateSourcaConfig  
 * @Description: 数据库配置 
 * @author Jiekai Lin 
 * @date 2018年5月12日 下午4:32:01
 */
@Configuration
@MapperScan(basePackages="com.xintu.sso.api.mapper")
public class DateSourcesConfig {

    @Value("${mybatis.datasource.driverClassName}")
    private String driverClassName;

    @Value("${mybatis.datasource.url}")
    private String jdbcUrl;

    @Value("${mybatis.datasource.username}")
    private String userName;

    @Value("${mybatis.datasource.password}")
    private String password;

    @Primary
    @Bean(name = "dataSource", destroyMethod = "close")
    @ConfigurationProperties(prefix = "mybatis.datasource")
    @RefreshScope
    public DataSource dataSource() {
        final HikariDataSource hds = new HikariDataSource();
        hds.setJdbcUrl(jdbcUrl);
        hds.setDriverClassName(driverClassName);
        hds.setUsername(userName);
        hds.setPassword(password);
        return hds;
    }


    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}