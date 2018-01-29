/*
 * CollectionUtilsExample.java 1.0.0 2018/01/26  14:08 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  14:08 created by xulihua
 */
package com.xulihuazj.businessimpl.example.util;

import org.apache.commons.collections.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtilsExample {

    @Test
    public void test1() {
        String foo = "测试";
        ArrayList arrayList = new ArrayList();
        arrayList.add("aaa");
        arrayList.add("bbb");
        boolean notEmpty = CollectionUtils.isNotEmpty(arrayList);
        System.out.println(notEmpty);
    }
}
