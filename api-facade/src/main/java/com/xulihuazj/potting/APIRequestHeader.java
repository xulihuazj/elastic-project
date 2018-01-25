/*
 * APIRequestHeader.java 1.0.0 2018/01/25  16:48 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  16:48 created by xulihua
 */
package com.xulihuazj.potting;

import com.xulihuazj.enums.common.Language;
import com.xulihuazj.enums.common.Source;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class APIRequestHeader {


    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "员工id", hidden = true)
    private String userId;

    /**
     * 语言
     */
    @ApiModelProperty(value = "系统语言（必传）", required = true)
    private Language language;

    /**
     * 来源
     */
    @ApiModelProperty(value = "请求来源（必传）", required = true)
    private Source source;

    @ApiModelProperty(value="设备唯一标识（移动设备必传）")
    private String deviceId;

    @ApiModelProperty(value="版本号（移动设备必传）")
    private String version;

    /**
     * 授权令牌
     */
    @ApiModelProperty(value = "授权令牌", hidden = true)
    private String token;

    /**
     * 用于接收来源信息
     */
    @ApiModelProperty(value = "统计信息（JSON字符串）（目前支持渠道{channel:appstore}）")
    private String dtMonitor;

}
