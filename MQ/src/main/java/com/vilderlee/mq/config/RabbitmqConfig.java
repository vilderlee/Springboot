package com.vilderlee.mq.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述:
 *
 * @package com.vilderlee.mq.config
 * @auther vilderlee
 * @date 2019-04-22 21:46
 */

@Configuration
public class RabbitmqConfig {


    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses("39.104.159.18:5672");
        cachingConnectionFactory.setUsername("lichao");
        cachingConnectionFactory.setPassword("lichao.5220246");
        cachingConnectionFactory.setPublisherConfirms(true);
        cachingConnectionFactory.setPublisherReturns(true);
        return cachingConnectionFactory;
    }


    @Bean
    public RabbitAdmin admin(CachingConnectionFactory cachingConnectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(cachingConnectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }


    @Bean
    public Exchange dlxExchange() {
        return new TopicExchange("dlxExchange", true, false, null);
    }

    @Bean
    public Queue dlxQueue() {
        Map<String, Object> arguments = new HashMap<>(3);
        arguments.put("x-message-ttl", 60000);
        arguments.put("x-dead-letter-exchange", "deadExchange");
        arguments.put("x-dead-letter-routing-key", "dead.letter.routing.key");
        return new Queue("dlxQueue", true, false, false, arguments);
    }

    @Bean
    public Binding dlxBinding() {
        return new Binding("dlxQueue", Binding.DestinationType.QUEUE, "dlxExchange", "dlx.letter.#", null);
    }

    @Bean
    public Binding ttlBinding() {
        return new Binding("deadQueue", Binding.DestinationType.QUEUE, "deadExchange", "dead.letter.#", null);
    }

    @Bean
    public Exchange ttlExchange() {
        return new TopicExchange("deadExchange", true, false, null);
    }

    @Bean
    public Queue ttlQueue() {
        return new Queue("deadQueue", true, false, false, null);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        //必须设置为true，否则return机制不生效
        template.setMandatory(true);
        //设置确认机制，确保消息到达broker
        template.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            if (ack) {
                System.out.println("--------消息发送成功---------");
            } else {
                System.out.println("--------消息发送失败---------");
//                System.out.println(correlationData.getReturnedMessage());
                System.out.println(cause);
            }
        });
        //return机制，没有路由到queue上去的时候
        template.setReturnCallback((Message message, int replyCode, String replyText,
                                    String exchange, String routingKey) -> {
            System.out.println("message：" + message);
            System.out.println("replyCode：" + replyCode);
            System.out.println("replyText：" + replyText);
            System.out.println("exchange：" + routingKey);
        });

        return template;
    }

    class DelegateMessage implements ChannelAwareMessageListener {
        public boolean handle(String message) {
            System.out.println("^^^^^^^body^^^^^^^" + message);
            return true;
        }

        @Override
        public void onMessage(Message message, Channel channel) throws Exception {
            boolean result = handle(new String(message.getBody()));
            if (result) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        }

    }

    class DelegateMessage2 {
        public boolean handle(String message) {
            System.out.println("^^^^^^^body^^^^^^^" + message);
            return true;
        }



    }


    @Bean
    public SimpleMessageListenerContainer container(CachingConnectionFactory cachingConnectionFactory) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(cachingConnectionFactory);
        listenerContainer.addQueues(ttlQueue());

        MessageListenerAdapter adapter = new MessageListenerAdapter();
        adapter.setDelegate(new DelegateMessage2());
        adapter.setDefaultListenerMethod("handle");

        listenerContainer.setMessageListener(adapter);
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return listenerContainer;
    }
}
