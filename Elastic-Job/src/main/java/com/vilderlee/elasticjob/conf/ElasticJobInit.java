package com.vilderlee.elasticjob.conf;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.vilderlee.elasticjob.annotation.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/10/23      Create this file
 * </pre>
 */
@Component
public class ElasticJobInit {

    @Autowired
    private CoordinatorRegistryCenter center;
    @Autowired
    private ApplicationContext context;


    @PostConstruct
    public void initJobScheduler(){
        Map<String, SimpleJob> beans = context.getBeansOfType(SimpleJob.class);
        beans.forEach((beanName, bean)->{
            Job job = bean.getClass().getAnnotation(Job.class);

            JobCoreConfiguration coreConfiguration = JobCoreConfiguration.newBuilder(job.jobName(), job.cron(),
                    job.shardingTotalCount())
                    .build();
            SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(coreConfiguration,
                    bean.getClass().getCanonicalName());
            new JobScheduler(center, LiteJobConfiguration.newBuilder(simpleJobConfiguration).build()).init();
        });

    }

}
