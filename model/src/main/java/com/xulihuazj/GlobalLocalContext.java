/*
 * GlobalLocalContext.java 1.0.0 2018/01/25  17:54 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:54 created by xulihua
 */
package com.xulihuazj;


import com.xulihuazj.enums.common.Language;
import com.xulihuazj.enums.common.Source;
import lombok.Data;

/**
 * @param <T>
 * @author xulihua
 * @description
 * @date 2017年8月3日下午4:15:34
 */
@Data
public class GlobalLocalContext {

    // 用户ID
    private String userId;

    // 语言
    private Language language;

    // 来源
    private Source source;

    //设备ID
    private String deviceId;

    // 版本信息
    private String version;

    // 用于接收来源信息
    private String dtMonitor;

    // 当前IP
    private String currentIp;

    // 幂等校验ID（从请求头中取出）
    private String requestId;

}