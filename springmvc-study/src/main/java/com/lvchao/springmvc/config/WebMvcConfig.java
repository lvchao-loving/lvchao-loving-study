package com.lvchao.springmvc.config;

import com.lvchao.springmvc.handler.TestHandlerInterceptor1;
import com.lvchao.springmvc.handler.TestHandlerInterceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/19
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {//WebMvcConfigurer {
    @Autowired
    private TestHandlerInterceptor1 testHandlerInterceptor1;

    @Autowired
    private TestHandlerInterceptor2 testHandlerInterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(testHandlerInterceptor1).addPathPatterns("/**");
        registry.addInterceptor(testHandlerInterceptor2).addPathPatterns("/**");

    }
}
