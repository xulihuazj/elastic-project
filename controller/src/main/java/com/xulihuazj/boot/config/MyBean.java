/*
 * MyBean.java 1.0.0 2017/12/16  23:07 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/16  23:07 created by xulihua
 */
package com.xulihuazj.boot.config;
//import org.springframework.boot.*;
//import org.springframework.stereotype.*

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description: 命令行启动器
 * @author: xulihua
 * @date: 2017/12/16 23:07
 */
@Component
public class MyBean implements CommandLineRunner {

    //如果一些CommandLineRunner beans被定义必须以特定的次序调用， 你可以额外实现org.springframework.core.Ordered接
    //口或使用org.springframework.core.annotation.Order注解
    @Override
    public void run(String... args) {
    // Do something...
    }
}