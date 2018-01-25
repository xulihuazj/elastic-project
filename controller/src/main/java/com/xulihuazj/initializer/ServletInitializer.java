/*
 * ServletInitializer.java 1.0.0 2018/01/25  19:55 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  19:55 created by xulihua
 */
package com.xulihuazj.initializer;

import com.xulihuazj.ApiApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        PropertiesInitializer.init(servletContext);
        super.onStartup(servletContext);
        //添加监听器
        servletContext.addListener(RequestContextListener.class);
        /*// 添加XSS过滤器
        FilterRegistration.Dynamic xssFilterRegistration = servletContext.addFilter("XssSqlFilter", XSSFilter.class);
        xssFilterRegistration
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "*//*");*/
    }
}
