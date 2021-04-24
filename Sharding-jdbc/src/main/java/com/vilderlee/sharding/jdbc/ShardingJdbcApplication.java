package com.vilderlee.sharding.jdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScans({
        @MapperScan(basePackages = "com.vilderlee.sharding.jdbc.mapper.noshard", sqlSessionFactoryRef =
                "noShardSqlSessionFactory")})
public class ShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }

}
