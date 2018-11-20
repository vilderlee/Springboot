package com.vilderlee.gateway.config;

import com.vilderlee.gateway.servlet.TestListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/15      Create this file
 * </pre>
 */
@Configuration
public class ListenerConfiguration {

    @Bean
    public ServletListenerRegistrationBean listenerRegister(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new TestListener());

        return servletListenerRegistrationBean;
    }
}
