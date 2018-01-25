package com.xulihuazj;/*
 * ToString.java 1.0.0 2017/6/18  下午6:17
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/6/18  下午6:17 created by yinqiang
 */


import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 所有的模型都需要继承此类 便于日志打印及监控
 */
@Data
public class ToString implements Serializable {

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
