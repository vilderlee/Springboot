package com.vilderlee.elasticjob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.management.ManagementFactory;

@SpringBootApplication
@Slf4j
public class ElasticJobDemoApplication {

    public static void main(String[] args) {

        String name = ManagementFactory.getRuntimeMXBean().getName();
        log.info("*************** 当前进程号 {}  ***************", name.split("@")[0]);
        System.setProperty("PID", name.split("@")[0]);

        ConfigurableApplicationContext applicationContext = SpringApplication.run(ElasticJobDemoApplication.class, args);

        applicationContext.getEnvironment().getSystemProperties().put("PID", name.split("@")[0]);

    }





}
