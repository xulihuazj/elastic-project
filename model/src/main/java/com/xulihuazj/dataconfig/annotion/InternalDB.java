/*
 * InternalDB.java 1.0.0 2018/1/20  20:27 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/1/20  20:27 created by xulihua
 */
package com.xulihuazj.dataconfig.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @author: xulihua
 * @date: 2018/1/20 20:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InternalDB {
}
