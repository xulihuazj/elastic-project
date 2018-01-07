/*
 * ErrorCode.java 1.0.0 2017/12/16  11:41 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/16  11:41 created by xulihua
 */
package com.xulihuazj.error;

/**
 * @Description: 异常码定义接口
 * @author: xulihua
 * @date: 2017/12/16 11:41
 */
public interface ErrorCode {

    //获取异常码
    String getCode();

    //获取异常信息
    String getMessage();

}
