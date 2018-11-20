package com.vilderlee.gateway.config;

import com.vilderlee.gateway.servlet.TestServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
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
public class ServletRegisterConfiguration {

    @Bean
    public ServletRegistrationBean servletRegister(){

        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new TestServlet(),"/TestServlet/*");
        return registrationBean;
    }
}
