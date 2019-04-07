package com.xintu.sso.services.configs;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class DateSourcaConfig {

    @Autowired
    private Environment env;

    /**  
    * @Title: getDataSource  
    * @Description: 创建数据源 ，destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
    * @param @return 
    * @return DataSource
    * @throws  
    */  
    @Bean(destroyMethod =  "close") 
    public DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();  
        dataSource.setUrl(env.getProperty("spring.datasource.url"));  
        dataSource.setUsername(env.getProperty("spring.datasource.username"));  
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));  
        dataSource.setInitialSize(10);//初始化时建立物理连接的个数  
        dataSource.setMaxActive(70);//最大连接池数量  
        dataSource.setMinIdle(0);//最小连接池数量  
        dataSource.setMaxWait(60000);//获取连接时最大等待时间，单位毫秒。  
        dataSource.setValidationQuery("SELECT 1  FROM DUAL");//用来检测连接是否有效的sql  
        dataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效  
        dataSource.setTestWhileIdle(true);//建议配置为true，不影响性能，并且保证安全性。  
        dataSource.setPoolPreparedStatements(false);//是否缓存preparedStatement，也就是PSCache 
        return dataSource;
    }

    /**  
    * @Title: sqlSessionFactory  
    * @Description:  根据数据源创建SqlSessionFactory 
    * @param @param ds
    * @param @return
    * @param @throws Exception 
    * @return SqlSessionFactory
    * @throws  
    */  
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setDataSource(ds);
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        sfb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        sfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        return sfb.getObject();
    }
}