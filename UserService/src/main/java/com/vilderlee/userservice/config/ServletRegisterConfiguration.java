package com.vilderlee.userservice.config;

import com.vilderlee.userservice.servlet.ServletI;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/29      Create this file
 * </pre>
 */
@Configuration
public class ServletRegisterConfiguration {
    @Bean
    public ServletRegistrationBean initServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new ServletI());
        servletRegistrationBean.addUrlMappings("/ServletI");
        return servletRegistrationBean;
    }
}
