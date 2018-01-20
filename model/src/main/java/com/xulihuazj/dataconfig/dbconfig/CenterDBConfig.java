/*
 * CenterdbConfig.java 1.0.0 2017/12/18  23:29 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/18  23:29 created by xulihua
 */
package com.xulihuazj.dataconfig.dbconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.xulihuazj.dataconfig.DataSourceConfig;
import com.xulihuazj.dataconfig.annotion.CenterDB;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 配置多数据源
 *
 * @Description: 第一个数据源（centerdb）
 * @author: xulihua
 * @date: 2017/12/18 23:29
 */
@Configuration
//扫描 包名前缀是CENTER_PACKAGE,EXTERNAL_PACKAGE,MAMAHOME_PACKAGE下的Mapper文件
@MapperScan(basePackages = {DataSourceConfig.CENTER_PACKAGE, DataSourceConfig.EXTERNAL_PACKAGE},
        annotationClass = CenterDB.class, sqlSessionFactoryRef = "centerdbSqlSessionFactory")
public class CenterDBConfig {

    static final String MAPPER_LOCATION = "classpath*:centerdb/*.xml";

    @Bean(name = "centerbdDataSource")
    @Primary
    //获取application.properties中前缀是centerdb.datasource的配置
    @ConfigurationProperties(prefix = "centerdb.datasource")
    public DataSource createCenterdbDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "centerdbTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManage() {
        return new DataSourceTransactionManager(createCenterdbDataSource());
    }

    @Bean(name = "centerdbTransactionTemplate")
    @Primary
    public TransactionTemplate centerDBTransactionTemplate() {
        return new TransactionTemplate(masterTransactionManage());
    }

    @Bean(name = "centerdbSqlSessionFactory")
    @Primary
    @Resource(name = "centerdbDataSource")
    public SqlSessionFactory centerdbSqlSessionFactory(DataSource centerdbDataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(centerdbDataSource);
        //添加pageHelper分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", Boolean.FALSE.toString());
        properties.setProperty("pageSizeZero", Boolean.TRUE.toString());
        properties.setProperty("supportMethodsArguments", Boolean.TRUE.toString());
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(CenterDBConfig.MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }


}
