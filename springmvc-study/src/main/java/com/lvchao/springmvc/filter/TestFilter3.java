package com.lvchao.springmvc.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/19
 */
public class TestFilter3 extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("OncePerRequestFilter执行... HttpServletRequest");
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
