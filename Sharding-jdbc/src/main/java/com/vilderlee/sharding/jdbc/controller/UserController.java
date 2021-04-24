package com.vilderlee.sharding.jdbc.controller;

import com.vilderlee.sharding.jdbc.mapper.noshard.UserMapper;
import com.vilderlee.sharding.jdbc.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @ClassName UserController
 * @Description
 * @Author VilderLee
 * @Date 2021/4/2 11:19 上午
 */

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @GetMapping("/transactionTest")
    public String transactionTest() {
        User user = new User();
        user.setId(100);
        userMapper.save(user);
         userMapper.findAll().toString();
        return "123";
    }

}
