/*
 * IOUtilsExample.java 1.0.0 2018/01/26  14:04 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  14:04 created by xulihua
 */
package com.xulihuazj.businessimpl.example;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

public class IOUtilsExample {

  /*  copy：这个方法可以拷贝流，算是这个工具类中使用最多的方法了。支持多种数据间的拷贝。
    copy内部使用的其实还是copyLarge方法。因为copy能拷贝Integer.MAX_VALUE的字节数据，即2^31-1*/

//    copy(inputstream,outputstream)
//    copy(inputstream,writer)
//    copy(inputstream,writer,encoding)
//    copy(reader,outputstream)
//    copy(reader,writer)
//    copy(reader,writer,encoding)

    /*copyLarge：这个方法适合拷贝较大的数据流，比如2G以上。*/
//    copyLarge(reader,writer) 默认会用1024*4的buffer来读取
//    copyLarge(reader,writer,buffer)


    /*获取输入流*/
//    //通过文本获取输入流 ， 可以指定编码格式
//    InputStream toInputStream(final String input, final Charset encoding)
//
//    InputStream toInputStream(final String input, final String encoding)
//
//    //获取一个缓冲输入流，默认缓冲大小 1KB
//    InputStream toBufferedInputStream(final InputStream input)
//
//    //获取一个指定缓冲流的大小的输入流
//    InputStream toBufferedInputStream(final InputStream input, int size)
//
//    //把流的全部内容放在另一个流中
//    BufferedReader toBufferedReader(final Reader reader)
//
//    //把流的全部内容放在另一个流中
//    BufferedReader toBufferedReader(final Reader reader, int size).


    /*获取输入流里面的内容*/
    // 输入流 --》 字符串
//    String toString(final InputStream input, final Charset encoding)
//
//    // 输入流 --》 字符串
//    String toString(final InputStream input, final String encoding)
//
//    // 字符输入流 --》 字符串
//    String toString(final Reader input)
//
//    // 字符数组 --》 字符串
//    String toString(final byte[] input, final String encoding)
//
//    //输入流 --》 字符数组
//    byte[] toByteArray(final InputStream input)
//
//    //输入流 --》 字符数组
//    byte[] toByteArray(final Reader input, final Charset encoding)
//
//    //输入流 --》 字符数组
//    byte[] toByteArray(final Reader input, final String encoding)
//
//    //URL   --》 字符数组
//    byte[] toByteArray(final URI uri)
//
//    // URL  --》 字符串
//    String toString(final URL url, final Charset encoding)
//
//    // URL  --》 字符串
//    String toString(final URL url, final String encoding)
//
//    // URLConnection --》 字符串
//    byte[] toByteArray(final URLConnection urlConn)


    /*write：这个方法可以把数据写入到输出流中*/

//    write(byte[] data, OutputStream output)
//    write(byte[] data, Writer output)
//    write(byte[] data, Writer output, Charset encoding)
//    write(byte[] data, Writer output, String encoding)
//
//    write(char[] data, OutputStream output)
//    write(char[] data, OutputStream output, Charset encoding)
//    write(char[] data, OutputStream output, String encoding)
//    write(char[] data, Writer output)
//
//    write(CharSequence data, OutputStream output)
//    write(CharSequence data, OutputStream output, Charset encoding)
//    write(CharSequence data, OutputStream output, String encoding)
//    write(CharSequence data, Writer output)
//
//    write(StringBuffer data, OutputStream output)
//    write(StringBuffer data, OutputStream output, String encoding)
//    write(StringBuffer data, Writer output)
//
//    write(String data, OutputStream output)
//    write(String data, OutputStream output, Charset encoding)
//    write(String data, OutputStream output, String encoding)
//    write(String data, Writer output)

    @Test
    public void test1() throws IOException {
        File file = new File("d:aaa.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        OutputStream outputStream = null;
        int copy = IOUtils.copy(fileInputStream, outputStream);
        System.out.println("copy流");
    }
}

