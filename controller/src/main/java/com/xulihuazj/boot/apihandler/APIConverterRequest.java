/*
 * APIConverterRequest.java 1.0.0 2018/01/08  16:17 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  16:17 created by xulihua
 */
package com.xulihuazj.boot.apihandler;

import java.lang.annotation.*;

/**
 * 自定义一个注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface APIConverterRequest {
}
