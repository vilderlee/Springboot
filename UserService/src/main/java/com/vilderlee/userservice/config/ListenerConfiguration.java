package com.vilderlee.userservice.config;

import com.vilderlee.userservice.web.WebListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:
 *
 * @package com.vilderlee.userservice.config
 * @auther vilderlee
 * @date 2018/11/29 8:41 PM
 */
@Configuration
public class ListenerConfiguration {

    @Bean
    public ServletListenerRegistrationBean initListener(){
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();
        registrationBean.setListener(new WebListener());
        return registrationBean;
    }
}
