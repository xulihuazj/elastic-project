/*
 * FilenameUtilsExample.java 1.0.0 2018/01/26  14:06 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  14:06 created by xulihua
 */
package com.xulihuazj.businessimpl.example;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

/**
 * 各种获取文件名称工具类
 */
public class FilenameUtilsExample {


    @Test
    public void test1() {
        String filePath = "d:/yezi/xiaoyezi.txt";
        //获取文件名称前缀
        System.out.println(FilenameUtils.getPrefix(filePath));
        //
        System.out.println(FilenameUtils.getExtension(filePath));

        System.out.println(FilenameUtils.getFullPathNoEndSeparator(filePath));

        System.out.println(FilenameUtils.getPathNoEndSeparator(filePath));

        System.out.println(FilenameUtils.getFullPath(filePath));

    }
}
