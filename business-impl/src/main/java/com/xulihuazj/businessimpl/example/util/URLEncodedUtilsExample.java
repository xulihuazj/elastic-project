/*
 * URLEncodedUtilsExample.java 1.0.0 2018/01/26  14:08 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  14:08 created by xulihua
 */
package com.xulihuazj.businessimpl.example.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class URLEncodedUtilsExample {

    @Test
    public void test1() {
        Model model = new Model();
        List<Model> modelList = new ArrayList<>();
        modelList.add(model);
        String format = URLEncodedUtils.format(modelList, "UTF-8");
        System.out.println(format);
    }

    @Test
    public void test2() {
        List<NameValuePair> parse = URLEncodedUtils.parse("fsdfsaaeljl;", Charset.forName("UTF-8"));
        System.out.println(parse);

    }

    private class Model implements NameValuePair {

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getValue() {
            return null;
        }
    }

}


