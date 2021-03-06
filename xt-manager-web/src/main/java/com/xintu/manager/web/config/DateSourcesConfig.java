package com.xintu.manager.web.config;

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

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Primary
    @Bean(name = "dataSource", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource")
    @RefreshScope
    public DataSource dataSource() {
        final HikariDataSource hds = new HikariDataSource();
        hds.setJdbcUrl(jdbcUrl);
        hds.setDriverClassName(driverClassName);
        hds.setUsername(userName);
        hds.setPassword(password);
        return hds;
    }

}