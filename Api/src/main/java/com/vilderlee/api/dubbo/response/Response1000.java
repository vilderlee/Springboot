package com.vilderlee.api.dubbo.response;

import com.vilderlee.api.domain.UserInfo;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * dell      2018/10/26        TODO
 * </pre>
 */
public class Response1000 extends BaseResponse {
    private UserInfo user;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @Override public String toString() {
        return "Response1000{" + "user=" + user.toString() + '}';
    }
}
