package com.vilderlee.sharding.jdbc.config;

import com.vilderlee.sharding.jdbc.mapper.noshard.ShardConfigMapper;
import com.vilderlee.sharding.jdbc.model.ShardConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/10/17      Create this file
 * </pre>
 */
@Component
@Slf4j
public class StandDatabasePreciseShardingAlgorithm1 implements PreciseShardingAlgorithm<String>,
        ApplicationContextAware {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String database = routDatabase(preciseShardingValue);
        if (!StringUtils.isEmpty(database)) {
            if (collection.contains(database)) {
                return database;
            } else {
                return (String) collection.toArray()[0];
            }
        } else {
            log.info("----->该分片键值找不到对应的分库,默认取第一个库，分片键是={}，逻辑表是={},分片值是={}", preciseShardingValue.getColumnName(),
                    preciseShardingValue.getLogicTableName(), preciseShardingValue.getValue());
            return (String) collection.toArray()[0];
        }
    }

    private String routDatabase(PreciseShardingValue<String> preciseShardingValue) {
        String date = preciseShardingValue.getValue();
        String year = date.substring(0,4);
        ShardConfigMapper shardConfigMapper = context.getBean(ShardConfigMapper.class);
        ShardConfig shardConfig = shardConfigMapper.findOneByConfigKey(year);
        String databaseName = null;
        if (null != shardConfig) {
            String configValue = shardConfig.getConfigValue();
            databaseName = configValue.split(",")[0];
        }
        return databaseName;
    }

    private ApplicationContext context;
    
    @Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
