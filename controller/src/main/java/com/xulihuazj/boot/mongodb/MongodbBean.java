/*
 * MongodbBean.java 1.0.0 2017/12/17  21:57 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/17  21:57 created by xulihua
 */
package com.xulihuazj.boot.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.MongoDbFactory;
import com.mongodb.DB;
/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/17 21:57
 */
@Component
public class MongodbBean {


    //MongoDB地址
    @Value("${spring.data.mongodb.host}")
    private String mongodbHost ;

    //MongoDB端口
    @Value("${spring.data.mongodb.port}")
    private String mongdbPort;

    private final MongoDbFactory mongo;

    public MongodbBean(MongoDbFactory mongo){
        this.mongo = mongo;
    }

    public void example(){
        DB db = mongo.getDb();
    }

}
