/*
 * StringEscapeUtilsExample.java 1.0.0 2018/01/26  14:07 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  14:07 created by xulihua
 */
package com.xulihuazj.businessimpl.example;
//import org.apache.commons.lang.StringEscapeUtils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

public class StringEscapeUtilsExample {

    @Test
    //转义/反转义html脚本
    public void test1() {
        String source = "tryuiop[[po;ilkghjkl";
        //
        String escapeHtml = StringEscapeUtils.escapeHtml4(source);

        //escapeEcmaScript/unescapeEcmaScript js转换
        System.out.println(StringEscapeUtils.escapeEcmaScript("<SCRIPT>alert('1111')</SCRIPT>"));

        //escapeJava/unescapeJava 把字符串转为unicode编码
        System.out.println(StringEscapeUtils.escapeJava("中国"));

        //字符串转成Json格式
        System.out.println(StringEscapeUtils.escapeJson("由于微信限制，无法增加链接，请进入原文查看"));

        //String 转 XML
        System.out.println(StringEscapeUtils.escapeXml11("由于微信限制，无法增加链接，请进入原文查看"));

        //string 转 csv
        System.out.println(StringEscapeUtils.escapeCsv("由于微信限制，无法增加链接，请进入原文查看"));

    }
}
