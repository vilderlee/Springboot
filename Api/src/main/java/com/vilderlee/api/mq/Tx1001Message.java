package com.vilderlee.api.mq;

import com.vilderlee.common.mq.BaseMessage;

/**
 * 功能描述:
 *
 * @package com.vilderlee.api.mq
 * @auther vilderlee
 * @date 2018/11/29 10:21 PM
 */
public class Tx1001Message extends BaseMessage{

    private static final long serialVersionUID = 5408478284415098937L;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Tx1001Message{" +
                "username='" + username + '\'' +
                '}';
    }
}
