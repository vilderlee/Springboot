package com.vilderlee.userservice.config;

import com.vilderlee.tools.serialize.ObjectSerializer;
import com.vilderlee.tools.util.TripleDESUtil;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.vilderlee.common.mq.Message;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/21      Create this file
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

    /**
     * 配置连接池的触发器
     *
     * @return
     */
    @Bean
    public AmqpAdmin initAmqpAdmin() throws Exception {
        return new RabbitAdmin(initCachingConnectionFactory());
    }

    @Bean
    public DirectExchange initDirectExchange(){
       return new DirectExchange("tx1001DirectExchange", true, true);
    }

    @Bean
    public Queue initQueue(){
        return new Queue("tx1001Queue", true);
    }

    @Bean
    public Binding initBinding(){
        return BindingBuilder.bind(initQueue()).to(initDirectExchange()).with("tx1001Queue");
    }

    @Bean()
    public RabbitTemplate init() throws Exception {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(initCachingConnectionFactory());
        rabbitTemplate.setExchange(initDirectExchange().getName());
        rabbitTemplate.setQueue(initQueue().getName());
        rabbitTemplate.setRoutingKey(initQueue().getName());
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {

            System.out.println("消息出错:");
            Message messageq = (Message) ObjectSerializer.bytes2Object(message.getBody());
            System.out.println(messageq.toString());
        });

        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause)->{
            System.out.println("消息已发送到broker！消息Id:" + correlationData.getId() );
        });

        return rabbitTemplate;
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