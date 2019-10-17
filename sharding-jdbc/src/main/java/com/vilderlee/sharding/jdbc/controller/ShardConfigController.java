package com.vilderlee.sharding.jdbc.controller;

import com.vilderlee.sharding.jdbc.mapper.ShardConfigMapper;
import com.vilderlee.sharding.jdbc.model.ShardConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@Slf4j
@RequestMapping("/shardConfig")
@Api(tags = "配置管理")
public class ShardConfigController {

    @Autowired
    private ShardConfigMapper shardConfigMapper;

    @GetMapping("/find/{key}")
    @ApiOperation("获取信息")
    @ApiImplicitParam(name = "key", value = "配置键", defaultValue = "2019", required = true)
    public ShardConfig findOneByKey(@PathVariable String key){
        return shardConfigMapper.findOneByConfigKey(key);
    }
}
