/*
 * StringUtilsExample.java 1.0.0 2018/01/26  14:05 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  14:05 created by xulihua
 */
package com.xulihuazj.businessimpl.example;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;

public class StringUtilsApacheExample {

    @Test
    public void test1() {

        String foo = "由于微信限制，无法增加";
        String foo2 = "a side effect of the null handling is that a NullPointerException should be considered a bug";

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~判断~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //是否不为空
        StringUtils.isNotBlank(foo);
        StringUtils.isBlank(foo);
        StringUtils.isEmpty(foo);
        //比较两个字符串，并返回它们不同的部分
        StringUtils.difference(foo2, "abcd");

        //是否相等
        StringUtils.equals(foo, "由于微信限制，无法增加");
        StringUtils.equalsIgnoreCase(foo, "由于微信限制，无法增加");


        //返回字符串长度
        StringUtils.length(foo2);
        //转小写，空返回null
        StringUtils.lowerCase(foo2);
        //转大写，空返回null
        StringUtils.upperCase(foo2);
        //4-20 最小字符
        StringUtils.mid(foo2, 4, 20);
        //用参数二 替换 参数一 一部分字符串
        StringUtils.overlay(foo2, "aaaaaa", 4, 20);


        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~移除，删除~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //删除所有空格符
        StringUtils.trim(foo2);
        StringUtils.deleteWhitespace(" s 中 你 4j");
        //移除开始部分的相同的字符
        StringUtils.removeStart("www.baidu.com", "www.");
        StringUtils.removeStartIgnoreCase("www.baidu.com", "WWW");
        //移除后面相同的部分
        StringUtils.removeEnd("www.baidu.com", ".com");
        StringUtils.removeEndIgnoreCase("www.baidu.com", ".COM");
        //移除所有相同的部分
        StringUtils.remove("www.baidu.com/baidu", "bai");
        //移除结尾字符为"\n", "\r", 或者 "\r\n".
        StringUtils.chomp("abcrabc\r");
        StringUtils.chomp("baidu.com", "com");
        StringUtils.chop("wwe.baidu");

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~替换~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //替换指定的字符，只替换第一次出现的
        StringUtils.replaceOnce("www.baidu.com/baidu", "baidu", "hao123");
        //替换所有出现过的字符
        StringUtils.replace("www.baidu.com/baidu", "baidu", "hao123");
        //也是替换，最后一个参数表示替换几个
        StringUtils.replace("www.baidu.com/baidu", "baidu", "hao123", 1);
        //二三参数对应的数组，查找二参数数组一样的值，替换三参数对应数组的值。本例:baidu替换为taobao。com替换为net
        StringUtils.replaceEach("www.baidu.com/baidu", new String[]{"baidu", "com"}, new String[]{"taobao", "net"});
        //字符串参数二和参数三对应替换.
        StringUtils.replaceChars("www.baidu.com", "bdm", "qo");
        //替换指定开始(参数三)和结束(参数四)中间的所有字符'
        StringUtils.overlay("www.baidu.com", "hao123", 4, 9);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~转换~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //转换第一个字符为大写.如何第一个字符是大写原始返回
        StringUtils.capitalize("Ddf");
        //转换第一个字符为大写.如何第一个字符是大写原始返回
        StringUtils.uncapitalize("DTf");
        //反向转换，大写变小写，小写变大写
        StringUtils.swapCase("I am Jiang, Hello");
        //将字符串倒序排列
        StringUtils.reverse("中国人民");
        //根据特定字符(二参数)分隔进行反转
        StringUtils.reverseDelimited("中:国:人民", ':');

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~替换~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //复制参数一的字符串，参数二为复制的次数
        StringUtils.repeat("ba", 3);
        //复制参数一的字符串，参数三为复制的次数。参数二为复制字符串中间的连接字符串
        StringUtils.repeat("ab", "ou", 3);
        //如何字符串长度小于参数二的值，末尾加空格补全。(小于字符串长度不处理返回)
        StringUtils.rightPad("海川", 4);
        //字符串长度小于二参数，末尾用参数三补上，多于的截取(截取补上的字符串)
        StringUtils.rightPad("海川", 4, "河流啊");
        //同上在前面补全空格
        StringUtils.leftPad("海川", 4);
        //字符串长度小于二参数，前面用参数三补上，多于的截取(截取补上的字符串)
        StringUtils.leftPad("海川", 4, "大家好");
        //字符串长度小于二参数。在两侧用空格平均补全（测试后面补空格优先）
        StringUtils.center("海川", 3);
        //字符串长度小于二参数。在两侧用三参数的字符串平均补全（测试后面补空格优先）
        StringUtils.center("海川", 5, "流");
        //只显示指定数量(二参数)的字符,后面以三个点补充(参数一截取+三个点=二参数)
        StringUtils.abbreviate("中华人民共和国", 5);
        //2头加点这个有点乱。本例结果: ...ijklmno
        StringUtils.abbreviate("abcdefghijklmno", 12, 10);
        //保留指定长度，最后一个字符前加点.本例结果: ab.f
        StringUtils.abbreviateMiddle("abcdef", ".", 4);


        //用空格分割成数组，null为null
        StringUtils.split(foo2);
        StringUtils.split(foo2, ',');
        StringUtils.split(foo2, "abc");
        //以指定字符分割成数组，第三个参数表示分隔成数组的长度，如果为0全体分割
        StringUtils.split("中华 ：人民：共和", "：", 2);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~去空格.去字符~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        //可能是对特殊空格符号去除？？
        StringUtils.strip("大家好  啊  \t");
        StringUtils.stripToNull(" \t");

        //如果第二个参数为null去空格(否则去掉字符串2边一样的字符，到不一样为止)
        StringUtils.strip("fsfsdf", "f");
        //如果第二个参数为null只去前面空格(否则去掉字符串前面一样的字符，到不一样为止)
        StringUtils.stripStart("ddsuuu ", "d");
        //如果第二个参数为null只去后面空格，(否则去掉字符串后面一样的字符，到不一样为止)
        StringUtils.stripEnd("dabads", "das");
        //对数组没个字符串进行去空格。
        StringUtils.stripAll(new String[]{" 中华 ", "民 国 ", "共和 "});
        //如果第二个参数为null.对数组每个字符串进行去空格。(否则去掉数组每个元素开始和结尾一样的字符)
        StringUtils.stripAll(new String[]{" 中华 ", "民 国", "国共和国"}, "国");


        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 字符串截取~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        //截取指定位置的字符，null返回null.""返回""
        StringUtils.substring("国民党", 2);
        //截取指定区间的字符
        StringUtils.substring("中国人民共和国", 2, 4);
        //从左截取指定长度的字符串
        StringUtils.left("说点什么好呢", 3);
        //从右截取指定长度的字符串
        StringUtils.right("说点什么好呢", 3);
        //从第几个开始截取，三参数表示截取的长度
        StringUtils.mid("说点什么好呢", 3, 2);
        //截取到等于第二个参数的字符串为止
        StringUtils.substringBefore("说点什么好呢", "好");
        //从左往右查到相等的字符开始，保留后边的，不包含等于的字符。本例：什么好呢
        StringUtils.substringAfter("说点什么好呢", "点");
        //这个也是截取到相等的字符，但是是从右往左.本例结果：说点什么好
        StringUtils.substringBeforeLast("说点什么好点呢", "点");
        //这个截取同上是从右往左。但是保留右边的字符
        StringUtils.substringAfterLast("说点什么好点呢？", "点");
        //截取查找到第一次的位置，和第二次的位置中间的字符。如果没找到第二个返回null。本例结果:2010世界杯在
        StringUtils.substringBetween("南非2010世界杯在南非，在南非", "南非");
        //返回参数二和参数三中间的字符串，返回数组形式
        StringUtils.substringsBetween("[a][b][c]", "[", "]");


        //是否包含字符、字符串
        StringUtils.contains(foo, 'a');
        StringUtils.contains(foo, "由于微信限制");
        //检查字符串是否包含给定字符组中的任何字符
        StringUtils.containsAny(foo2, new char[]{'a', 'b'});
        StringUtils.containsAny(foo2, "abcd");
        //检查字符串是否不包含 字符数组中任一字符
        StringUtils.containsNone(foo2, new char[]{'a', 'b'});
        StringUtils.containsNone(foo2, "abcd");
        //检查字符串中字符 是否 只在字符数组中
        StringUtils.containsOnly(foo2, new char[]{'a', 'b'});
        StringUtils.containsOnly(foo2, "abcd");

        //计算子字符串在较大字符串中出现的次数
        StringUtils.countMatches(foo2, "abcd");


        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~查找~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //检查字符串是否以指定的后缀结尾
        StringUtils.endsWith(foo2, "bug");
        StringUtils.endsWithIgnoreCase(foo2, "BUG");

        //取数组每个元素共同的部分字符串
        StringUtils.getCommonPrefix(new String[]{"bd", "ac", "cd"});

        //统计参数一中每个字符与参数二中每个字符不同部分的字符个数
        StringUtils.getLevenshteinDistance(foo2, "abcd");


        //普通查找字符，如果一参数为null或者""返回-1
        StringUtils.indexOf(foo2, 'b');

        StringUtils.indexOf(foo2, "bug");

        StringUtils.indexOf(foo2, 'a');
        StringUtils.indexOfAny(foo2, new char[]{'a', 'b'});
        StringUtils.indexOfAny(foo2, "bugaaa");
        StringUtils.indexOfAnyBut(foo2, new char[]{'a', 'b'});
        StringUtils.indexOfAnyBut(foo2, "bugaaa");
        StringUtils.indexOfDifference(new String[]{"bd", "ac", "cd"});
        StringUtils.indexOfDifference(foo2, "bug");

        StringUtils.lastOrdinalIndexOf("yksdfdht", "f", 2);

        //检查是否CharSequence的只包含Unicode的字母。空将返回false。一个空的CharSequence（长（）= 0）将返回true
        StringUtils.isAlpha("这是干什么的2");
        //检查是否只包含Unicode的CharSequence的字母和空格（''）。空将返回一个空的CharSequence假（长（）= 0）将返回true。
        StringUtils.isAlphaSpace("NBA直播 ");
        //检查是否只包含Unicode的CharSequence的字母或数字。空将返回false。一个空的CharSequence（长（）= 0）将返回true。
        StringUtils.isAlphanumeric("NBA直播");
        //如果检查的Unicode CharSequence的只包含字母，数字或空格（''）。空将返回false。一个空的CharSequence（长（）= 0）将返回true。
        StringUtils.isAlphanumericSpace("NBA直播");

        //检查是否只包含数值。
        StringUtils.isNumeric(foo2);
        //检查是否只包含数值或者空格
        StringUtils.isNumericSpace(foo2);

        //拼接
        StringUtils.join(new ArrayList<String>(), foo2);

    }
}
