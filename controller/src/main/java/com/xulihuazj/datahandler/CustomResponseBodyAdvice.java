/*
 * CustomResponseBodyAdvice.java 1.0.0 2018/01/25  18:16 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  18:16 created by xulihua
 */
package com.xulihuazj.datahandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.xulihuazj.emoj.EmojiUtils;
import com.xulihuazj.json.JSONHelper;
import com.xulihuazj.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author 徐礼华
 * @description 响应同一处理
 * @date 2017/10/15 22:50
 */
@Order(1)
@ControllerAdvice(basePackages = {"com.cf.global.api.externalapi", "com.cf.global.api.internalapi"})
class CustomResponseBodyAdvice implements ResponseBodyAdvice {

    private Logger logger = LogManager.getLogger(CustomResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (null == body) {
            return null;
        } else {
            try {
                ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
                String json = objectMapper.writeValueAsString(body);
                String emojStr = EmojiUtils.emojiRecovery(json);
                return JSONHelper.toObject(emojStr, Object.class);
            } catch (JsonProcessingException e) {
                LogHelper.exception(e, logger, "响应同一处理，表情处理错误");
                return null;
            }
        }
    }
}