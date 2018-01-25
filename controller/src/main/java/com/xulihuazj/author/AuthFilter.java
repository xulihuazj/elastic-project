/*
 * AuthFilter.java 1.0.0 2018/01/25  17:51 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/25  17:51 created by xulihua
 */
package com.xulihuazj.author;

import com.xulihuazj.LocalContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 身份认证filter
 */
@Component
public class AuthFilter implements Filter {

    private Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        LocalContextHolder.setContext(LocalContextHolder.createEmptyContext());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}