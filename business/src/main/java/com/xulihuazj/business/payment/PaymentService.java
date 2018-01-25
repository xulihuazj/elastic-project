/*
 * PaymentService.java 1.0.0 2018/01/25  17:09 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:09 created by xulihua
 */
package com.xulihuazj.business.payment;

import com.xulihuazj.business.task.TaskSubBiz;
import com.xulihuazj.request.ShareRequest;
import com.xulihuazj.response.ShareResponse;

import java.util.Map;

public interface PaymentService extends TaskSubBiz {

    //支付宝支付：H5
    String alipayH5(String orderId, String returnUrl);

    //支付宝支付：APP
    String alipayApp(String orderId, String returnUrl);

    //支付宝支付： WEB
    String alipayWeb(String orderId, String returnUrl);

    //微信支付: H5、公众号
    String weixinH5(String orderId, String returnUrl);

    //微信支付： APP
    String weixinApp(String orderId, String returnUrl);

    //微信支付： WEB
    String weixinWeb(String orderId, String returnUrl);

    //mamahome 转发请求
    String forwardRequest(String data, Map<String, String> alipayData, String channel);

    //查询订单
    Map<String, String> orderStatusQuery(String channel, String orderId, String payStatus);

    //回调通知
    String notifyForword(Map<String, String> data, String channel);

    //获取微信KEY
    String getWeixinAppKey();

    //获取支付宝KEY
    String getAlipayPublicKey();

    //获取 WEB网站私钥
    String getSellerKey();

    //获取微信分享加密字段
    ShareResponse shareInfo(ShareRequest request);

}