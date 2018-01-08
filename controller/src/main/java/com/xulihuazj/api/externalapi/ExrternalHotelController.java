/*
 * ExrternalHotelController.java 1.0.0 2018/01/08  16:05 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/08  16:05 created by xulihua
 */
package com.xulihuazj.api.externalapi;


import com.xulihuazj.boot.apihandler.APIConverterRequest;
import com.xulihuazj.business.hotel.HotelService;
import com.xulihuazj.business.mq.ActiveMqProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/hotel")
@Api(value = "外部房源相关服务", tags = {"Client_Hotel"}, description = "外部房源相关服务")
public class ExrternalHotelController {

    @Resource
    private HotelService hotelServiceImpl;

    @Autowired
    private ActiveMqProducerService producerServiceImpl;


    @GetMapping(value = "/hotel/list/query")
    @ApiOperation(value = "客户端房源查询登录（徐礼华）", notes = "客户端房源查询登录")
    @APIConverterRequest
    public String inlandHotelList() {
        int i = hotelServiceImpl.selectListTest("EFFECTIVE");
        System.out.println(i);
        return "success";
    }

    @GetMapping("/activemq/send")
    public String activemq(String msg) {
        msg = StringUtils.isEmpty(msg) ? "This is Empty Msg." : msg;

        try {
            producerServiceImpl.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Activemq has sent OK.";
    }


}
