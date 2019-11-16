package com.vilderlee.cache.listener;

import com.vilderlee.cache.kafka.KafkaConsumerThread;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 功能描述:
 *
 * @package com.vilderlee.cache.listener
 * @auther vilderlee
 * @date 2019/11/9 8:36 下午
 */
@WebListener
@Order
public class InitListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        WebApplicationContext context = WebApplicationContextUtils.findWebApplicationContext(sce.getServletContext());
        ApplicationContext.setContext(context);
        new Thread(new KafkaConsumerThread("cache-Message")).start();
    }
}
