package com.vilderlee.eshopinventory.controller;

import com.vilderlee.eshopinventory.dto.User;
import com.vilderlee.eshopinventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.controller
 * @auther vilderlee
 * @date 2019/8/26 11:37 下午
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id){
        return userService.getUser(id);
    }

    @GetMapping("/user/list")
    public List<User> getAll(){
        return userService.getAll();
    }
}
