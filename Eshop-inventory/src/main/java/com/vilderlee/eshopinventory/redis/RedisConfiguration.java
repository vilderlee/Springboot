package com.vilderlee.eshopinventory.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.redis
 * @auther vilderlee
 * @date 2019/9/18 9:56 下午
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public JedisCluster jedisCluster(){
        Set<HostAndPort> nodes  = new HashSet<>();
        nodes.add(new HostAndPort("192.168.33.11",6379));
        nodes.add(new HostAndPort("192.168.33.12",6379));
        nodes.add(new HostAndPort("192.168.33.13",6379));
        return new JedisCluster(nodes);
    }

}
