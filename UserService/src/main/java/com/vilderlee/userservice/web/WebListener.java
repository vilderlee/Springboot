package com.vilderlee.userservice.web;


import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static com.vilderlee.common.environment.Environment.APPLICATION_CONTEXT;

/**
 * 功能描述:
 *
 * @package com.vilderlee.userservice.web
 * @auther vilderlee
 * @date 2018/11/29 8:48 PM
 */
public class WebListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        APPLICATION_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
