package com.vilderlee.gateway.config;//package com.springboot.consumer.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.web.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.web.config.annotation.WebMvcConfigurer;
//import org.springframework.web.web.view.InternalResourceViewResolver;
//import org.springframework.web.web.view.JstlView;
//
///**
// * <pre>
// * Modify Information:
// * Author        Date          Description
// * ============ ============= ============================
// * VilderLee    2018/11/2      Create this file
// * </pre>
// */
//@Configuration
//public class WebConfiguration implements WebMvcConfigurer {
//    @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/").addResourceLocations("classpath:/static/");
//    }
//
//
//    @Bean public InternalResourceViewResolver initViewResolver(){
//        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
//        resourceViewResolver.setPrefix("/templates/");
//        resourceViewResolver.setSuffix(".html");
//        resourceViewResolver.setViewClass(JstlView.class);
//
//        return resourceViewResolver;
//    }
//}
