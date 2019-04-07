package com.xintu.sso.services.configs;
import javax.sql.DataSource;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

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


    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}