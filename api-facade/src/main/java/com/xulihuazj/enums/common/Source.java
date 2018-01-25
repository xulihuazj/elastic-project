/*
 * Source.java 1.0.0 2018/01/25  16:50 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  16:50 created by xulihua
 */
package com.xulihuazj.enums.common;


import com.xulihuazj.enums.BaseEnum;

/**
 * 来源渠道
 */
public enum Source implements BaseEnum {

    WEB("WEB","网站"),

    ANDROID("ANDROID","安卓APP"),

    IOS("IOS","IOSAPP"),

    H5("H5","H5"),

    INTERNAL_WEB("INTERNAL_WEB","后台管理系统"),

    SUPPLIER_WEB("SUPPLIER_WEB","供应商管理系统"),

    ;

    Source(String code,String desc){
        this.code=code;
        this.desc=desc;
    }

    private String code;
    private String desc;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
