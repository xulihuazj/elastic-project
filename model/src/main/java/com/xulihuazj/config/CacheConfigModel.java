/*
 * CacheConfigModel.java 1.0.0 2018/01/23  15:29 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/23  15:29 created by xulihua
 */
package com.xulihuazj.config;

/**
 * Redis配置项
 */
public class CacheConfigModel {

    // redis 服务地址
    private String host;

    // redis 服务端口
    private int port;

    // 用户名
    private String userName;

    // 密码
    private String password;

    // 最大空闲连接数
    private int maxIdle;

    // 最大连接数
    private int maxTotal;

    // 当调用borrow Object方法时，是否进行有效性检查
    private boolean testOnBorrow;

    // 当调用return Object方法时，是否进行有效性检查
    private boolean testOnReturn;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }
}
