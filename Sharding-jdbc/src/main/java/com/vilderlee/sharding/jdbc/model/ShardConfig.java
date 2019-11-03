package com.vilderlee.sharding.jdbc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

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
@Data
@ApiModel
@AllArgsConstructor
public class ShardConfig {

    /**
     * 配置键
     */
    @ApiModelProperty(value = "配置键")
    private String configKey;

    /**
     * 配置表
     */
    @ApiModelProperty(value = "配置表")
    private String configValue;
}
