/*
 * EntityUtilsExample.java 1.0.0 2018/01/26  14:05 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  14:05 created by xulihua
 */
package com.xulihuazj.businessimpl.example;

import com.xulihuazj.model.user.UserInfoModel;
import org.apache.http.HttpEntity;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class EntityUtilsExample {

    @Test
    public void test1() throws IOException {
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setEmail("980774387@qq.com");
        HttpEntity httpEntity = new BasicHttpEntity();
        String string = EntityUtils.toString(httpEntity);
        System.out.println(string);
    }
}
