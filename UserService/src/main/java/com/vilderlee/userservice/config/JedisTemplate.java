package com.vilderlee.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/5      Create this file
 * </pre>
 */
@Component("jedisTemplate")
public class JedisTemplate {

    @Autowired
    private Jedis jedis;

    public String setString(String key,String value){
        return jedis.set(key, value);
    }

    public String getString(String key){
        return jedis.get(key);
    }
}
