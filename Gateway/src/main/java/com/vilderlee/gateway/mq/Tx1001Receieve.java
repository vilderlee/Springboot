package com.vilderlee.gateway.mq;

import com.vilderlee.api.mq.Tx1001Message;
import org.springframework.stereotype.Component;

/**
 * 功能描述:
 *
 * @package com.vilderlee.gateway.mq
 * @auther vilderlee
 * @date 2018/11/29 11:04 PM
 */
@Component("tx1001Receieve")
public class Tx1001Receieve {
    public void handle(Tx1001Message message){
        System.out.println(message.toString());
    }
}
