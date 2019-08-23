package com.mybatis.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


/**
 * @program: mybatis-demo
 * @description:
 * @author: Liu.Jie
 * @create: 2019-08-22 09:53
 **/
@Configuration
@Component
@MapperScan(basePackages = MyBatisConfiguration.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisConfiguration {

    static final String PACKAGE = "com.mybatis.demo.dao";
    static final String CONFIG_LOCATION = "classpath:mybatis/mybatis-config.xml";
    static final String MAPPING_LOCATION = "classpath:mapper/*.xml";

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean(name = "dataSource")
    @Primary
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);

        dataSource.setInitialSize(5);
        dataSource.setMaxActive(30);
        dataSource.setMinIdle(5);
        dataSource.setMaxWait(60000);
        return dataSource;
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.mybatis.demo");
        /*sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver() .getResources(MasterDataSourceConfig.MAPPER_LOCATION));*/


        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sessionFactory.setConfigLocation(resolver.getResource(MyBatisConfiguration.CONFIG_LOCATION));
            sessionFactory.setMapperLocations(resolver.getResources(MyBatisConfiguration.MAPPING_LOCATION));
            return sessionFactory.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
