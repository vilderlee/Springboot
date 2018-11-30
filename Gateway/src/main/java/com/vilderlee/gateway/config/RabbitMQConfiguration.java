package com.vilderlee.gateway.config;

import com.vilderlee.gateway.mq.Tx1001Receieve;
import com.vilderlee.tools.util.TripleDESUtil;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/29      Create this file
 * </pre>
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
@PropertySource("classpath:rabbitmq.properties")
public class RabbitMQConfiguration {
    private String host;
    private int port;
    private String username;
    private String password;
    private String virtualHost;
    private boolean publisherConfirms;
    private boolean publisherReturns;
    private int connectionTimeout;
    /**
     *
     * 初始化rabbitMQ的连接池
     *
     * @return
     */
    @Bean
    public CachingConnectionFactory initCachingConnectionFactory() throws Exception {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(TripleDESUtil.des3DecodeCBC(username));
        connectionFactory.setPassword(TripleDESUtil.des3DecodeCBC(password));
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(publisherConfirms);
        connectionFactory.setPublisherReturns(publisherReturns);
        connectionFactory.setConnectionTimeout(connectionTimeout);
        return connectionFactory;
    }


    @Bean
    public MessageListenerAdapter initMessageListenerAdapter(){
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new Tx1001Receieve());
        messageListenerAdapter.setDefaultListenerMethod("handle");
        return messageListenerAdapter;
    }

    @Bean
    public SimpleMessageListenerContainer initSimpleMessageListenerContainer() throws Exception {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(initCachingConnectionFactory());
        simpleMessageListenerContainer.setMessageListener(initMessageListenerAdapter());
        simpleMessageListenerContainer.setQueueNames("tx1001Queue");
        return simpleMessageListenerContainer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public boolean isPublisherConfirms() {
        return publisherConfirms;
    }

    public void setPublisherConfirms(boolean publisherConfirms) {
        this.publisherConfirms = publisherConfirms;
    }

    public boolean isPublisherReturns() {
        return publisherReturns;
    }

    public void setPublisherReturns(boolean publisherReturns) {
        this.publisherReturns = publisherReturns;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}
