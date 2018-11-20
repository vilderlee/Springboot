package com.vilderlee.api.dubbo.request;

import java.io.Serializable;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * dell      2018/10/26        TODO
 * </pre>
 */
public abstract class BaseRequest implements Serializable {

    private static final long serialVersionUID = 715425594808341711L;

    private String transCode;

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }
}
