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
public class Request1000 extends BaseRequest {

    private String userName;
    private String mobilePhone;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
