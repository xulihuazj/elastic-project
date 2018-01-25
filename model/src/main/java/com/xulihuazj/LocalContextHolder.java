/*
 * LocalContextHolder.java 1.0.0 2018/01/25  17:53 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:53 created by xulihua
 */
package com.xulihuazj;

/**
 * @author xulihua
 * @description
 * @date 2017年8月24日上午9:37:31
 */
public class LocalContextHolder {

    private static final ThreadLocal<GlobalLocalContext> localContextHolder = new ThreadLocal<>();

    /**
     * 清除线程中的context
     */
    public void clearContext() {
        localContextHolder.remove();
    }

    /**
     * 获取当前线程Context
     */
    public static GlobalLocalContext getContext() {
        GlobalLocalContext ctx = localContextHolder.get();
        if (ctx == null) {
            ctx = createEmptyContext();
            localContextHolder.set(ctx);
        }
        return ctx;
    }

    public static void setContext(GlobalLocalContext context) {
        localContextHolder.set(context);
    }

    /**
     * 创建空context
     */
    public static GlobalLocalContext createEmptyContext() {
        return new GlobalLocalContext();
    }
}