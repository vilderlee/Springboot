package com.vilderlee.common.mq;

import java.io.Serializable;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/29      Create this file
 * </pre>
 */
public abstract class BaseMessage implements Serializable {

    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
