package com.vilderlee.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.vilderlee.elasticjob.annotation.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/10/22      Create this file
 * </pre>
 */
@Slf4j
@Component
@Job(jobName = "MyElasticJob", cron = "0/15 * * * * ?", shardingTotalCount = 1)
public class MyElasticJob implements SimpleJob {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostConstruct
    public void init(){
        System.out.println(jdbcTemplate);
    }

    @Override public void execute(ShardingContext shardingContext) {
        String name = ManagementFactory.getRuntimeMXBean().getName();

        System.out.printf("shardingContext.getShardingItem() is %d", shardingContext.getShardingItem());

        jdbcTemplate.update("INSERT INTO batch VALUES (?,?,?)", 3, new Date(System.currentTimeMillis()),
                name.split("@")[0]);

    }
}
