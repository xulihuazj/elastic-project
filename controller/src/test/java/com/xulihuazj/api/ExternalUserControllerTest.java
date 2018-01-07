/*
 * ExternalUserControllerTest.java 1.0.0 2017/12/17  22:28 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/17  22:28 created by xulihua
 */
package com.xulihuazj.api;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/17 22:28
 */

@RunWith(SpringJunit4ClassRunner.class)
@SpringApplicationConfiguration(classes= SampleDataJpaApplication.class)
//为@WebIntegrationTest添加环境变量属性来改变应用服务器端口号
@WebIntegrationTest({"server.port=0", "management.port=0"})

public class ExternalUserControllerTest {


    @Autowired
    private UserRepository userRepositoryImpl;




}
