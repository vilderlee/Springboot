//package com.vilderlee.userservice.config;
//
//import com.vilderlee.tools.util.TripleDESUtil;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * <pre>
// * Modify Information:
// * Author        Date          Description
// * ============ ============= ============================
// * VilderLee    2018/11/5      Create this file
// * </pre>
// *
// * @Author VilderLee
// */
//@Configuration
//@ConfigurationProperties(prefix = "springboot.redis")
//@PropertySource("classpath:redis.properties")
//@EnableCaching
//public class JedisConfiguration extends CachingConfigurerSupport {
//
//    private String host;
//    private int port;
//    private int timeout;
//    private String password;
//    private int maxIdle;
//    private int minIdle;
//    private int maxTotal;
//
//    public JedisPoolConfig initJedisPoolConfig() {
//
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMinIdle(minIdle);
//        jedisPoolConfig.setMaxTotal(maxTotal);
//        return jedisPoolConfig;
//    }
//
//    @Bean public Jedis initJedis() throws Exception {
//        JedisPool jedisPool = new JedisPool(initJedisPoolConfig(), host, port, timeout, TripleDESUtil.des3DecodeCBC(password));
//        return jedisPool.getResource();
//    }
//
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public int getTimeout() {
//        return timeout;
//    }
//
//    public void setTimeout(int timeout) {
//        this.timeout = timeout;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getMaxIdle() {
//        return maxIdle;
//    }
//
//    public void setMaxIdle(int maxIdle) {
//        this.maxIdle = maxIdle;
//    }
//
//    public int getMinIdle() {
//        return minIdle;
//    }
//
//    public void setMinIdle(int minIdle) {
//        this.minIdle = minIdle;
//    }
//
//    public int getMaxTotal() {
//        return maxTotal;
//    }
//
//    public void setMaxTotal(int maxTotal) {
//        this.maxTotal = maxTotal;
//    }
//}
