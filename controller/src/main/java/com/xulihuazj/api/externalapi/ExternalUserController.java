/*
 * ExternalUserController.java 1.0.0 2017/12/16  15:31 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/16  15:31 created by xulihua
 */
package com.xulihuazj.api.externalapi;

import com.xulihuazj.boot.apihandler.APIConverterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/16 15:31
 */
@RestController
@EnableAutoConfiguration
//这个注解告诉Spring Boot根据添加的jar依赖猜测你想如何配置Spring。由于 spring-boot-starter-web 添加了Tomcat和Spring MVC， 所以auto-configuration将假定你正在开发一个web应用并相应地
//对Spring进行设置
@RequestMapping(value = "/user")
@Api(value = "外部用户相关服务", tags = { "Client_User" }, description = "外部用户相关服务")
public class ExternalUserController {


    @APIConverterRequest
    @ApiOperation(value = "客户端用户登录（徐礼华）", notes = "客户端用户登录")
    /*@RequestMapping 注解提供路由信息。 它告诉Spring任何来自"/client/login"路径的HTTP请求都应该被映射到
    externalUserLogin 方法。*/
    @GetMapping(value = "/client/login")
    public void externalUserLogin(){

    }



}
