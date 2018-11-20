package com.vilderlee.gateway.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/15      Create this file
 * </pre>
 */
public class TestListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
    }

    @Override public void contextDestroyed(ServletContextEvent sce) {

    }
}
