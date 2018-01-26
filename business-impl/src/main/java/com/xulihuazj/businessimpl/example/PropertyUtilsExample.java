/*
 * PropertyUtilsExample.java 1.0.0 2018/01/26  14:10 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  14:10 created by xulihua
 */
package com.xulihuazj.businessimpl.example;

import com.xulihuazj.model.user.UserInfoModel;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class PropertyUtilsExample {

    @Test
    //getSimpleProperty()通过反射读取属性
    public void test1() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ;
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setEmail("aaaa@qq.com");
        String email = (String) PropertyUtils.getSimpleProperty(userInfoModel, "email");
        System.out.println(email);
    }

    @Test
    //copyProperty()复制Bean属性,只复制引用，final类型和原始类型（primitive type）
    public void test2() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ;
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setEmail("aaaa@qq.com");
        UserInfoModel copyUserModel = new UserInfoModel();
        PropertyUtils.copyProperties(userInfoModel, copyUserModel);
    }


    @Test
    //describe()创建包含Bean属性的Map
    public void test3() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setEmail("aaaa@qq.com");

        Map<String, Object> propMap = PropertyUtils.describe(userInfoModel);
        //邮箱
        System.out.println(propMap.get("email"));
        //姓名
        System.out.println(propMap.get("name"));
    }


}
