/*
 * AppBeanConfig.java 1.0.0 2017/12/16  21:38 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/16  21:38 created by xulihua
 */
package com.xulihuazj.boot.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xulihuazj.boot.FooCompent;
import com.xulihuazj.boot.filter.MyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.DispatcherType;
import java.util.EnumSet;


/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/16 21:38
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class AppBeanConfig implements ExitCodeGenerator {

    @Value("${random.value}")
    private long secret1;

    @Value("${random.int}")
    private long secret2;

    @Value("${random.long}")
    private long secret3;

    @Value("${random.int(10)}")
    private long secret4;

    @Value("${random.int[1024,65536]")
    private long secret5;

    //覆盖序列化发
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper()
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)//只为null的属性不展示
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)//未知属性不展示
                ;
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);//蛇形展示
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);//属性为 空（“”） 或者为 NULL 都不序列化
        return objectMapper;
    }

    //任何以foo为前缀的属性定义都会映射到FooComponent上
    @ConfigurationProperties(prefix = "foo")
    @Bean
    public FooCompent fooCompent(){
        return null;
    }

    //当容器退出时，返回Bean的退出码
    @Override
    public int getExitCode() {//如果beans想在应用结束时返回一个特定的退出码（exit code）
        return 0;
    }

    //自定义HttpMessageConverter
    @Bean
    public HttpMessageConverters customConverters(){
      /*  HttpMessageConverter<?> addtional = new HttpMeesageConverter(){
        };
        HttpMessageConverter<?> another = ;
        return new HttpMessageConverters(addtional,another);*/
        return null;
    }

    //自定义异常跳转页面400
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new MyCustomeizer();
    }
    private static class MyCustomeizer implements EmbeddedServletContainerCustomizer{

        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/400"));
        }
    }

    //如果你为一个路径注册一个ErrorPage， 最终被一个过滤器（Filter） 处理（对于一些非Spring web框架， 像Jersey和
    //Wicket这很常见） ， 然后过滤器需要显式注册为一个ERROR分发器（dispatcher） 。
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registrationBean;
    }





}


