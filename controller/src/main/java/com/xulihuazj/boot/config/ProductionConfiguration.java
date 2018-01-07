/*
 * ProductionConfiguration.java 1.0.0 2017/12/17  17:51 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/17  17:51 created by xulihua
 */
package com.xulihuazj.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/17 17:51
 */

//Spring Profiles提供了一种隔离应用程序配置的方式， 并让这些配置只能在特定的环境下生效。 任何@Component或
//@Configuration都能被@Profile标记， 从而限制加载它的时机
@Configuration
@Profile("production")
public class ProductionConfiguration {



}
