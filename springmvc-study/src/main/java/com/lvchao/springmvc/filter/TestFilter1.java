package com.lvchao.springmvc.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/19
 */

// @WebFilter(urlPatterns = { "/lvchao/*"}, filterName = "TestFilter1")
public class TestFilter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("/lvchao/*...init方法");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("/lvchao/*...doFilter方法");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("/lvchao/*...destroy方法");
    }
}
