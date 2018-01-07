/*
 * CenterdbConfig.java 1.0.0 2017/12/18  23:29 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/18  23:29 created by xulihua
 */
package com.xulihuazj.dataconfig;

import sun.applet.Main;

import javax.management.MXBean;
import javax.sql.DataSource;

/**
 * 配置多数据源
 * @Description: 第一个数据源（centerdb）
 * @author: xulihua
 * @date: 2017/12/18 23:29
 */
@Configuration
//扫描 包名前缀是CENTER_PACKAGE,EXTERNAL_PACKAGE,MAMAHOME_PACKAGE下的Mapper文件
@MapperScan(basePackages={DataSourceConfig.CENTER_PACKAGE,DataSourceConfig.EXTERNAL_PACKAGE,
    DataSourceConfig.MAMAHOME_PACKAGE},annotationClass=CenterDB.class,sqlSessionFactoryRef="centerdbSqlSessionFactory")
public class CenterdbConfig {


    @Bean
    @Primary
    //获取application.properties中前缀是centerdb.datasource的配置
    @ConfigurationProperties(prefix="centerdb.datasource")
    public DataSource createCenterdbDataSource(){
        return new DruidDataSource();
    }

/*    @Bean
    //获取application.properties中前缀是internal.datasource的配置
    @ConfigurationProperties(prefix="internal.datasource")
    public DataSource createInternalDataSource(){
        return new
    }
    */

    public DataSourceTransactionManager createDatasoureTransactionManage(){


    }






}
