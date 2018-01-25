/*
 * MailSettings.java 1.0.0 2018/01/25  18:04 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  18:04 created by xulihua
 */
package com.xulihuazj.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮箱配置
 *
 * @author xulihua
 */
@Component
@ConfigurationProperties(prefix = "mail")
@Data
public class MailSettings {

    //邮箱服务器 端口
    private Integer port;

    //邮箱服务器 地址
    private String host;

    private String protocol;

    //邮箱账户
    private String username;

    //邮箱密码
    private String password;


}
