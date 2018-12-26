package com.vilderlee.api.domain;

import com.vilderlee.common.annotation.validate.Mobile;
import com.vilderlee.common.annotation.validate.Nullable;

import java.io.Serializable;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * dell      2018/10/29        TODO
 * </pre>
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 4463272346329037768L;
    @Nullable(description = "用户名")
    private String userName;
    private String password;
    private String userId;

    @Nullable(description = "手机号码")
    @Mobile
    private String mobilePhone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override public String toString() {
        return "UserInfo{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + ", userId='" + userId + '\'' + ", mobilePhone='" + mobilePhone
                + '\'' + '}';
    }
}
