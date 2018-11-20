package com.vilderlee.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vilderlee.api.domain.UserInfo;
import com.vilderlee.api.dubbo.request.Request1000;
import com.vilderlee.api.dubbo.response.Response1000;
import com.vilderlee.api.dubbo.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * dell      2018/10/29        TODO
 * </pre>
 */
@RestController public class UserController {

    @Reference private UserInfoService userInfoService;

    @RequestMapping("/getUserInfo.do") public UserInfo getUser() {
        Request1000 request1000 = new Request1000();
        request1000.setTransCode("1000");
        request1000.setUserId("0001");
        Response1000 response1000 = userInfoService.tx1000(request1000);

        UserInfo userInfo = response1000.getUser();
        return userInfo;
    }
}