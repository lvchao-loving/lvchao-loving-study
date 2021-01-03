package com.lvchao.springmvc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Description:
 *
 * @author lvchao
 * @date Create on 2020/12/19
 */
@WebListener
public class TestServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("TestServletContextListener......contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("TestServletContextListener......contextDestroyed");
    }
}
