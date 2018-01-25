/*
 * Swagger2Initializer.java 1.0.0 2018/01/25  17:39 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:39 created by xulihua
 */
package com.xulihuazj.initializer;


import com.xulihuazj.config.OfflineEnvCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Conditional(OfflineEnvCondition.class)
public class Swagger2Initializer {

    @Value("${api.environment}")
    private String environment;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.cf.global.api")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("mamahome RESTful APIs").version("1.0").
                description("API身份认证，需要在Request Header中增加key为 【Authorization】的值，value为登录API返回的token值. ##所有API请求参数采用下划线命名规则").build();
    }
}
