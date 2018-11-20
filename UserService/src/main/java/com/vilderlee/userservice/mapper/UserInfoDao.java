package com.vilderlee.userservice.mapper;

import com.vilderlee.api.domain.UserInfo;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * VilderLee      2018/10/29        TODO
 * </pre>
 */
public interface UserInfoDao {
    UserInfo getUserInfo(String userId);
}
