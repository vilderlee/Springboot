package com.vilderlee.userservice.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * VilderLee      2018/10/29        TODO
 * </pre>
 */
@Configuration
@ConfigurationProperties(value = "springboot.dubbo")
@PropertySource("classpath:dubbo.properties")
public class DubboConfiguration {

    private String name;
    private boolean isCheck;
    private String protocol;
    private String address;
    private String protocolName;
    private int protocolPort;

    @Bean
    public ApplicationConfig initApplicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(name);
        return applicationConfig;
    }

    @Bean
    public ConsumerConfig initConsumerConfig(){
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(isCheck);
        return consumerConfig;
    }

    @Bean
    public RegistryConfig initRegistryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setCheck(isCheck);
        registryConfig.setAddress(address);
        registryConfig.setProtocol(protocol);

        return registryConfig;
    }

    @Bean
    public ProtocolConfig initProtocolConfig(){

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
        return protocolConfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public int getProtocolPort() {
        return protocolPort;
    }

    public void setProtocolPort(int protocolPort) {
        this.protocolPort = protocolPort;
    }
}
