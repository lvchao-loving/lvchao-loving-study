package com.lvchao.springmvc.config;

import com.lvchao.springmvc.filter.TestFilter1;
import com.lvchao.springmvc.filter.TestFilter2;
import com.lvchao.springmvc.filter.TestFilter3;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/19
 */
@Configuration
public class FilterConfig{
    @Bean
    public FilterRegistrationBean buildTestFilter1() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(3);
        filterRegistrationBean.setFilter(new TestFilter1());
        filterRegistrationBean.setName("testFilter1");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean buildTestFilter2() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(4);
        filterRegistrationBean.setFilter(new TestFilter2());
        filterRegistrationBean.setName("testFilter2");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean buildTestFilter3() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(5);
        filterRegistrationBean.setFilter(new TestFilter3());
        filterRegistrationBean.setName("testFilter3");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}
