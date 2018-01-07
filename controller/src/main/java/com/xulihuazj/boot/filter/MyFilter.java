/*
 * MyFilter.java 1.0.0 2017/12/17  20:55 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/12/17  20:55 created by xulihua
 */
package com.xulihuazj.boot.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description:
 * @author: xulihua
 * @date: 2017/12/17 20:55
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("自定义过滤器");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
