package com.vilderlee.clickhousedemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ClickhouseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClickhouseDemoApplication.class, args);
    }

    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("/test")
    public void test() {
        userInfoMapper.createTable();
    }

    @GetMapping("/test2")
    public void test2() {
        List<Users> query = userInfoMapper.query();
        System.out.println(query);
    }


    @GetMapping("/test3")
    public String test3() {
        userInfoMapper.delete();
        return "";
    }


    @GetMapping("/update")
    public String update() {
        userInfoMapper.update("lichao", "vilderli");
        return "";
    }

    @GetMapping("/sql")
    public String sql(){
        Map map = new HashMap();
        List list = new ArrayList();
        list.add("5656565656");
        list.add("562131231231231232156565656");

        map.put("username", list);
        map.put("id", 1);
        String sql = "alter table users update arrays = #{username} where id = #{id}";
        map.put("sql", sql);
        userInfoMapper.updateSql(map);
        return "";
    }


    @GetMapping("/desc")
    public String desc() {
        List<Map<String, Object>> users = userInfoMapper.desc("users");
        return users.toString();
    }


    @GetMapping("/descDB")
    public String descDB() {
        List<DatabasesDesc> users = userInfoMapper.descTable("tutorial", "users");
        return users.toString();
    }
}
