package com.vilderlee.eshopinventory.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.redis
 * @auther vilderlee
 * @date 2019/9/18 9:52 下午
 */
@Repository("redisDAOImpl")
public class RedisDAOImpl implements RedisDAO{

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void set(String key, String value) {
        jedisCluster.set(key,value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }
}
