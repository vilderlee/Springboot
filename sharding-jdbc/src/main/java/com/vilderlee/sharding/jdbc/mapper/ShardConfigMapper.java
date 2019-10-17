package com.vilderlee.sharding.jdbc.mapper;

import com.vilderlee.sharding.jdbc.model.ShardConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
public interface ShardConfigMapper {

    /**
     * findOneByConfigKey
     * @param configKey String
     * @return com.vilderlee.shardingjdbc.model.ShardConfig
     */
    ShardConfig findOneByConfigKey(String configKey);

    /**
     * selectAll
     * @param keys
     * @return java.util.List<com.vilderlee.shardingjdbc.model.ShardConfig>
     */
    List<ShardConfig> findAllByKey(List<String> keys);

}
