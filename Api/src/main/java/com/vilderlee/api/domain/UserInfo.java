package com.vilderlee.api.domain;

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
    private String userName;
    private String userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override public String toString() {
        return "UserInfo{" + "userName='" + userName + '\'' + ", userId='" + userId + '\'' + '}';
    }
}
