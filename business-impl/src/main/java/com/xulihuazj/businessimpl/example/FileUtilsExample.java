/*
 * FileUtilsExample.java 1.0.0 2018/01/26  13:57 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  13:57 created by xulihua
 */
package com.xulihuazj.businessimpl.example;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtils操作示例
 */
public class FileUtilsExample {


    //写入
    @Test
    public void test1() throws IOException {

        /* 写文件
         * 1.这里只列出3种方式全参数形式，api提供部分参数的方法重载
         * 2.最后一个布尔参数都是是否是追加模式
         * 3.如果目标文件不存在，FileUtils会自动创建
         * */
        //static void:write(File file, CharSequence data, String encoding, boolean append)
        FileUtils.write(new File("D:/a/b/cxyapi.txt"), "程序换api", "UTF-8", true);

        //static void:writeLines(File file, Collection<?> lines, boolean append)
        List<String> lineList = new ArrayList<>();
        lineList.add("欢迎访问:");
        lineList.add("www.cxyapi.com");
        FileUtils.writeLines(new File("D:/a/b/cxyapi.txt"), lineList, true);

        //static void:writeStringToFile(File file, String data, String encoding, boolean append)
        FileUtils.writeStringToFile(new File("D:/a/b/cxyapi.txt"), "作者：cxy", "UTF-8", true);
    }

    //读取
    @Test
    public void test2() throws IOException {
        //读文件
        //static String:readFileToString(File file, String encoding)
        System.out.println(FileUtils.readFileToString(new File("D:/a/b/cxyapi.txt"), "UTF-8"));

        //static List<String>:readLines(File file, String encoding)
        //返回一个list
        System.out.println(FileUtils.readLines(new File("D:/a/b/cxyapi.txt"), "UTF-8"));
    }

    //删除
    @Test
    public void test3() throws IOException {
        //删除目录
        //static void:deleteDirectory(File directory)
        FileUtils.deleteDirectory(new File("D:/not/cxyapi"));

        //static boolean:deleteQuietly(File file)
        FileUtils.deleteQuietly(new File("D:/not/cxyapi")); //文件夹不是空任然可以被删除，永远不会抛出异常
    }

    //移动
    @Test
    public void test4() throws IOException {
        //移动文件 或 文件夹
        //static void：moveDirectory(File srcDir, File destDir)
        FileUtils.moveDirectory(new File("D:/cxyapi1"), new File("D:/cxyapi2")); //注意这里 第二个参数文件不存在会引发异常
        //static void:moveDirectoryToDirectory(File src, File destDir, boolean createDestDir)
        FileUtils.moveDirectoryToDirectory(new File("D:/cxyapi2"), new File("D:/cxyapi3"), true);
        /* 上面两个方法的不同是：
         * moveDirectory：D:/cxyapi2里的内容是D:/cxyapi1的内容。
         * moveDirectoryToDirectory：D:/cxyapi2文件夹移动到到D:/cxyapi3里
         *
         * 下面的3个都比较简单没提供示例，只提供了api
         * 其中moveToDirectory和其他的区别是 它能自动识别操作文件还是文件夹
         */
        //static void:moveFileToDirectory(srcFile, destDir, createDestDir)
        //static void:moveFile(File srcFile, File destFile)
        //static void:moveToDirectory(File src, File destDir, boolean createDestDir)
    }

    //拷贝
    @Test
    public void test5() throws IOException {
        //结果是cxyapi和cxyapi1在同一目录
        FileUtils.copyDirectory(new File("D:/cxyapi"), new File("D:/cxyapi1"));
        //结果是将cxyapi拷贝到cxyapi2下
        FileUtils.copyDirectoryToDirectory(new File("D:/cxyapi"), new File("D:/cxyapi2"));

        //拷贝文件
        FileUtils.copyFile(new File("d:/cxyapi.xml"), new File("d:/cxyapi.xml.bak"));
        //拷贝文件到目录中
        FileUtils.copyFileToDirectory(new File("d:/cxyapi.xml"), new File("d:/cxyapi"));
        //拷贝url到文件
        FileUtils.copyURLToFile(new URL("http://www.cxyapi.com/rss/cxyapi.xml"), new File("d:/cxyapi.xml"));
    }

    //其他
    @Test
    public void test6() throws IOException {
        //判断是否包含文件或者文件夹
        boolean b = FileUtils.directoryContains(new File("D:/cxyapi"), new File("D:/cxyapi/cxyapi.txt"));
        System.out.println(b);

        //获得临时目录 和 用户目录
        System.out.println(FileUtils.getTempDirectoryPath());
        System.out.println(FileUtils.getUserDirectoryPath());

        //打开流，如果不存在创建文件及其目录结构
        //第二个参数表示 文件流是否是追加方式
        FileOutputStream fos = FileUtils.openOutputStream(new File("D:/cxyapi/cxyapi.txt"), true);
        fos.write("欢迎访问：www.cxyapi.com\r\n".getBytes());
        fos.close();

        //文件 或 文件夹大小
        System.out.println(FileUtils.sizeOf(new File("D:/cxyapi")));
        System.out.println(FileUtils.sizeOfDirectory(new File("D:/cxyapi")));
    }


}
