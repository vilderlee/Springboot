package com.vilderlee.userservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.vilderlee.api.domain.UserInfo;
import com.vilderlee.api.dubbo.request.Request1000;
import com.vilderlee.api.dubbo.request.Request1001;
import com.vilderlee.api.dubbo.response.Response1000;
import com.vilderlee.api.dubbo.response.Response1001;
import com.vilderlee.api.dubbo.service.UserInfoService;
import com.vilderlee.userservice.mapper.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * VilderLee      2018/10/29        TODO
 * </pre>
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override public Response1000 tx1000(Request1000 request1000) {
        Response1000 response1000  = new Response1000();
        return response1000;
    }

    @Override public Response1001 tx1001(Request1001 request1001) {
        Response1001 response1001 = new Response1001();
        response1001.setUser(userInfoDao.getUserInfo(request1001.getUserId()));
        return response1001;
    }
}
