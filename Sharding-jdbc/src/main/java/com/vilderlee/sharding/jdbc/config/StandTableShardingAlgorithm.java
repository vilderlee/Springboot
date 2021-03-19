package com.vilderlee.sharding.jdbc.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

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
public class StandTableShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> collection,
            PreciseShardingValue<Integer> preciseShardingValue) {
        int id = preciseShardingValue.getValue();
        if ((id & 1) == 1){
            return (String) collection.toArray()[0];
        }else {
            return (String) collection.toArray()[1];
        }
    }
}
