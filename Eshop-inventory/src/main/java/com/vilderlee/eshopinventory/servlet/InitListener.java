package com.vilderlee.eshopinventory.servlet;

import com.vilderlee.eshopinventory.thread.RequestProcessThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.servlet
 * @auther vilderlee
 * @date 2019/9/19 10:17 下午
 */
@WebListener
public class InitListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("ServletListener is initial");
        RequestProcessThreadPool.init();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
