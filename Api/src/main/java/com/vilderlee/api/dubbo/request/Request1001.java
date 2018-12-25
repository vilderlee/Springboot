package com.vilderlee.api.dubbo.request;

import com.vilderlee.common.dubbo.BaseRequest;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * dell      2018/10/26        TODO
 * </pre>
 */
public class Request1001 extends BaseRequest {

    private String userId;
    private String mobile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override public String toString() {
        return "Request1001{" + "userId='" + userId + '\'' + ", mobile='" + mobile + '\'' + '}';
    }
}
