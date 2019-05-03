package com.jeeplus.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@MapperScan("com.jeeplus.*")
@Configuration
@Import(DynamicDataSourceRegister.class)
public class MyBatisConfig {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**SqlSessionFactory名称.*/
    public final static String SESSIONFACTORY_NAME = "sqlSessionFactory";
    /**mapper包路径，必须与其他SqlSessionFactory-mapper路径区分.*/
    public final static String MAPPER_PACKAGE = "com.jeeplus.*";
    /**mapper.xml文件路径，必须与其他SqlSessionFactory-mapper路径区分.*/
    public final static String MAPPER_XML_PATH = "classpath:/mappings/**/*.xml";

    @Autowired
    private DataSourceProperties dataSourceProperties;


    @Resource
    @Qualifier("dataSource1")
    private  DataSource dataSource;


//    @Bean(name = "dataSource")
//    public DataSource dataSource() {
//        //建议封装成单独的类
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(dataSourceProperties.getUrl());
//        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
//        dataSource.setUsername(dataSourceProperties.getUsername());
//        dataSource.setPassword(dataSourceProperties.getPassword());
//
////        logger.info(dataSourceProperties.getUrl());
////        logger.info(dataSourceProperties.getDriverClassName());
////        logger.info(dataSourceProperties.getUsername());
////        logger.info(dataSourceProperties.getPassword());
//        //logger.debug(dataSourceProperties.getType().toString());
//
//        return dataSource;
//
//    }

    //默认Bean首字母小写，简化配置
    //将SqlSessionFactory作为Bean注入到Spring容器中，成为配置一部分。
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_PATH));
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }




}
