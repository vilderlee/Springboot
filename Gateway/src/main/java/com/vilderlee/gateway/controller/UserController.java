package com.vilderlee.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vilderlee.api.domain.UserInfo;
import com.vilderlee.api.dubbo.request.Request1000;
import com.vilderlee.api.dubbo.request.Request1001;
import com.vilderlee.api.dubbo.response.Response1000;
import com.vilderlee.api.dubbo.response.Response1001;
import com.vilderlee.api.dubbo.service.UserInfoService;
import com.vilderlee.common.annotation.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * dell      2018/10/29        Create this file
 * </pre>
 */
@RestController public class UserController {
    /**
     * 异步调用，可以通过Future返回值得到
     */
    @Reference
    private UserInfoService userInfoService;


    @RequestMapping(value = "/postUser",method = RequestMethod.POST)
    public boolean postUser(@Validate UserInfo user){
        Request1000 request1000 = new Request1000();
        BeanUtils.copyProperties(user, request1000);
        Response1000 response1000 = userInfoService.tx1000(request1000);
        return response1000.isSuccess();
    }



    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET) public UserInfo getUser() {
        Request1001 request1001 = new Request1001();
        request1001.setTransCode("1000");
        request1001.setUserId("0001");
        Response1001 response1001 = userInfoService.tx1001(request1001);
        UserInfo userInfo = response1001.getUser();
        return userInfo;
    }

}