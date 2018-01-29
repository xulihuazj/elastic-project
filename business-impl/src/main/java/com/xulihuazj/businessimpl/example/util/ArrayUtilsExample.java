/*
 * ArrayUtils.java 1.0.0 2018/01/26  14:07 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  14:07 created by xulihua
 */
package com.xulihuazj.businessimpl.example.util;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

public class ArrayUtilsExample {

//    ArrayUtils 拥有以下方法:
//    toString
//            将一个数组转换成String,用于打印数组

//    isEquals
//            判断两个数组是否相等,采用EqualsBuilder进行判断

//    toMap
//    将一个数组转换成Map,如果数组里是Entry则其Key与Value就是新Map的Key和Value,如果是Object[]则Object[0]为KeyObject[1]为Value

//            clone
//    拷贝数组

//            subarray
//    截取子数组

//            isSameLength
//    判断两个数组长度是否相等

//            getLength
//    获得数组的长度

//            isSameType
//    判段两个数组的类型是否相同

//            reverse
//    数组反转

//            indexOf
//    查询某个Object在数组中的位置,可以指定起始搜索位置

//            lastIndexOf
//    反向查询某个Object在数组中的位置,可以指定起始搜索位置

//            contains
//    查询某个Object是否在数组中

//            toObject
//    将基本数据类型转换成外包型数据

//            isEmpty
//    判断数组是否为空(null和length=0的时候都为空)

//    addAll
//            合并两个数组

//    add
//            添加一个数据到数组

//    remove
//            删除数组中某个位置上的数据

//    removeElement
//    删除数组中某个对象(从正序开始搜索,删除第一个)

    //数组追加数组，不重复
    @Test
    public void test1() {
        int[] src = new int[1];
        int[] arr = new int[2];

        //	查询某个Object是否在数组中
        boolean contains = ArrayUtils.contains(new int[]{3, 1, 2}, 1);// true
        System.out.println(contains);

        // 浅层clone数组
        int[] newArray = ArrayUtils.clone(src);
        for (int anArr : arr) {
            if (!ArrayUtils.contains(newArray, anArr)) {
                newArray = ArrayUtils.add(newArray, anArr);
            }
        }
    }

    @Test
    public void test2(){

    }
}
