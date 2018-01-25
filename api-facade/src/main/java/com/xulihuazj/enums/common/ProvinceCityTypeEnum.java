/*
 * ProvinceCityTypeEnum.java 1.0.0 2018/01/25  16:52 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  16:52 created by xulihua
 */
package com.xulihuazj.enums.common;

import com.xulihuazj.enums.BaseEnum;

public enum ProvinceCityTypeEnum implements BaseEnum {

    PROVINCE("PROVINCE","省份"),

    CITY("CITY","城市"),

    REGION("REGION","地区"),
    ;

    private String code;
    private String desc;

    ProvinceCityTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
