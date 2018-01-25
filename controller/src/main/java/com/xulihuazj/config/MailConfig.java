/*
 * MailConfig.java 1.0.0 2018/01/25  18:15 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  18:15 created by xulihua
 */
package com.xulihuazj.config;

import com.xulihuazj.mail.MailUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @author xulihua
 * @Date 2017年7月11日下午5:00:37
 */
@Configuration
public class MailConfig {

    @Bean
    public MailUtil mailUtil(){
        return new MailUtil();
    }

/*    @Bean
    public OrderCurrentStatusEnum initCurrentStatus(){
        return OrderCurrentStatusEnum.YES_SUBMIT_UNPAID;
    }*/

}
