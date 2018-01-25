/*
 * EmojiUtils.java 1.0.0 2018/01/25  18:45 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  18:45 created by xulihua
 */
package com.xulihuazj.emoj;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.xulihuazj.log.LogHelper;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiUtils {

    private static Logger logger = LogManager.getLogger(EmojiUtils.class);

    /**
     * @param
     * @return 转换后字符串
     * @description 将字符串中的emoji表情转换成可以在utf-8字符集数据库中保存的格式（表情占4个字节，需要utf8mb4字符集）
     */
    public static String emojiConvert(String emjiStr) {
        if (StringUtils.isBlank(emjiStr)) {
            return null;
        }
        String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(emjiStr);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(stringBuffer, "[<[" + URLEncoder.encode(matcher.group(1), Constant.DEFAULT_ENCODING_UTF8) + "]>]");
            } catch (UnsupportedEncodingException e) {
                LogHelper.exception(e, logger, "转换表情异常");
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /**
     * @return 转换前的字符串
     * @Description 还原utf8数据库中保存的含转换后emoji表情的字符串
     * 转换后的字符串
     */
    public static String emojiRecovery(String emjiStr) {
        if (StringUtils.isBlank(emjiStr)) {
            return null;
        }
        String patternString = "\\[\\<\\[(.*?)\\]\\>\\]";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(emjiStr);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(stringBuffer, URLDecoder.decode(matcher.group(1), Constant.DEFAULT_ENCODING_UTF8));
            } catch (UnsupportedEncodingException e) {
                LogHelper.exception(e, logger, "转换表情异常");
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }

    public static Map<String, Double> getLngAndLat(String address) throws Exception {
        Map<String, Double> map = new HashMap<String, Double>();
/*        String url = "http://api.map.baidu.com/geocoder?address="+address+"&city=上海"
                + "&output=json&key=8azVgQbZR9irKHBOsqMzi8CAT7l1gtjt\n";*/

        //通过地址搜索经纬度
        //  String url=  "http://api.map.baidu.com/geocoder/v2/?address="+address+"&ret_coordtype=bd09ll&city=上海市&output=json&ak=8azVgQbZR9irKHBOsqMzi8CAT7l1gtjt\n";
        //通过经纬度搜索地址
        //String url=  "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=121.28422074860106,31.27943876895126&output=json&pois=1&ak=8azVgQbZR9irKHBOsqMzi8CAT7l1gtjt\n";
        //String url="http://api.map.baidu.com/place/v2/suggestion?query=11号线江苏路地铁站&region=上海市&city_limit=true&output=json&ak=8azVgQbZR9irKHBOsqMzi8CAT7l1gtjt";
        //String url="http://api.map.baidu.com/place/v2/search?query=宝山路&tag=地铁&region=上海&output=json&ak=8azVgQbZR9irKHBOsqMzi8CAT7l1gtjt";


        //高德
        //  String url="http://restapi.amap.com/v3/geocode/geo?address=上海市11号线江苏路地铁站&output=XML&key=bb73e98f2a6c5d2d2f3f8017b6a5b144";
        String url = "http://restapi.amap.com/v3/place/text?key=5d0e1dbca7a2465fd9d5213c3d1a112d&keywords=3号线曹杨路&types=地铁站&city=上海&children=1&offset=20&page=1&extensions=base";
        String json = loadJSON(url);
        int first = json.indexOf("<location>");
        int end = json.indexOf("</location>");
/*        System.out.println("经纬度：" + json.substring(first,end).substring(10,json.substring(first,end).length()));

        String test="1号线\\|莘庄";
        String[] ss =test.split("\\|");
        System.out.println("经度：" + test.split("\\|")[1] );*/


        JSONObject obj = JSONObject.fromObject(json);
        if (obj.get("status").toString().equals("1")) {
            if (obj.getJSONArray("pois").size() > 0) {
                for (int j = 0; j < obj.getJSONArray("pois").size(); j++) {
                    JSONObject jsonObject = obj.getJSONArray("pois").getJSONObject(j);
                    if (jsonObject != null) {
                        if (jsonObject.get("name").toString().indexOf("曹杨路") != -1) {
                            if (jsonObject.get("address").toString().indexOf("3号线;4号线") != -1) {
                                System.out.println(jsonObject.get("location").toString());
                            }
                        }
                    }
                }

/*                for(int i=0;i<obj.getJSONArray("results").size();i++){
                    JSONObject data = JSONObject.fromObject(obj.getJSONArray("results").get(i));
                    Map<String,Object> maptest=JSONHelper.toObject(data.toString(),Map.class);
                    if(maptest.get("name").equals("莘庄")){
                        Map<String,String> mapData =JSONHelper.toObject(maptest.get("location").toString(),Map.class);
                    }
                }*/
            }

            //     double lng = obj.getJSONArray("results").getJSONObject("location").getDouble("lng");
            // double lat = obj.getJSONObject("results").getJSONObject("location").getDouble("lat");
            // map.put("lng", lng);
            //   map.put("lat", lat);
            //  System.out.println("经度：" + lng + "---纬度：" + lat);
        } else {
            System.out.println("未找到相匹配的经纬度！");
            throw new Exception();
        }
        return map;
    }

    public static void main(String[] args) throws Exception {

//经度：104.11990831333591---纬度：30.69755718802188
        // getLngAndLat("2号线江苏路地铁站");
        System.out.println(traffic("1号线", "莘庄"));
    }

    public static String traffic(String lineName, String station) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

                /* 读入TXT文件 */
/*            String pathname = "G:\\traffic.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename),"gbk"); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line2 = "";
            Map<String, Double> map = new HashMap<String, Double>();*/
            //   while (line != null) {
            //    line = br.readLine(); // 一次读入一行数据
            //  String[] date = line.split(",");
            //  for (int i = 0; i < date.length; i++) {
            String url = "http://restapi.amap.com/v3/place/text?key=5d0e1dbca7a2465fd9d5213c3d1a112d&keywords=" + lineName + station + "&types=地铁站&city=上海市&children=1&offset=20&page=1&extensions=base";
            String json = loadJSON(url);
            //   System.out.println("线路：" +lineName + "---站名：" +station);
            JSONObject obj = JSONObject.fromObject(json);
            if (obj.get("status").toString().equals("1")) {
                if (obj.getJSONArray("pois").size() > 0) {
                    for (int j = 0; j < obj.getJSONArray("pois").size(); j++) {
                        JSONObject jsonObject = obj.getJSONArray("pois").getJSONObject(j);
                        if (jsonObject != null) {
                            if (jsonObject.get("name").toString().indexOf(station) != -1) {
                                if (jsonObject.get("address").toString().equalsIgnoreCase(lineName)) {
                                    System.out.println(jsonObject.get("location").toString());
                                    return jsonObject.get("location").toString();
                                }
                                if (jsonObject.get("address").toString().indexOf("3号线;4号线") != -1) {
                                    System.out.println(jsonObject.get("location").toString());
                                    return jsonObject.get("location").toString();
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("未找到相匹配的经纬度！---------");
                    System.out.println("线路：" + lineName + "---站名：" + station);
                    System.out.println("未找到相匹配的经纬度！---------");
                    return null;
                }
            }
            // }
            //   }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}