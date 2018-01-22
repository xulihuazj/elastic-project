/*
 * JSONHelper.java 1.0.0 2018/1/21  17:33 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/1/21  17:33 created by xulihua
 */
package com.xulihuazj.json;

/**
 * @Description:
 * @author: xulihua
 * @date: 2018/1/21 17:33
 */

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

/**
 * JSON辅助类
 */
public class JSONHelper {

    /**
     * 系列化json
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object);
    }

    /**
     * 反序列化object
     * @param json
     * @return
     */
    public static <T> T toObject(String json, Class<T> tClass) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return JSON.parseObject(json, tClass);
    }

}