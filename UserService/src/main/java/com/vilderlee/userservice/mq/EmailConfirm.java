package com.vilderlee.userservice.mq;


import com.vilderlee.api.mq.Tx1001Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/29      Create this file
 * </pre>
 */
@Component("emailConfirm")
public class EmailConfirm {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void tx1001(String body){
        Tx1001Message tx1001Message = new Tx1001Message();
        tx1001Message.setUsername(body);
        rabbitTemplate.convertAndSend(tx1001Message);
    }
}
