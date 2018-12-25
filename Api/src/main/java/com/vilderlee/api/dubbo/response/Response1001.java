package com.vilderlee.api.dubbo.response;

import com.vilderlee.api.domain.UserInfo;
import com.vilderlee.common.dubbo.BaseResponse;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * VilderLee      2018/10/26        TODO
 * </pre>
 */
public class Response1001 extends BaseResponse {

    private static final long serialVersionUID = -3147659417946422141L;

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
