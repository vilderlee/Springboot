package com.vilderlee.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/2      Create this file
 * </pre>
 */
@Controller
public class HelloController {

    @RequestMapping("/hello.do")
    public String hello(){
        return "hello";
    }
}
