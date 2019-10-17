package com.vilderlee.sharding.jdbc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

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
public class Orders {
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private String id;

    /**
     *  业务平台的订单id
     */
    @ApiModelProperty(value = "业务平台的订单id")
    private String parentOrdersUuid;
    /**
     * 业务平台的订单编号
     */
    @ApiModelProperty(value = "业务平台的订单编号")
    private String parentOrdersId;
    /**
     * 订单来源
     */
    @ApiModelProperty(value = "订单来源")
    private String orderOrigin;
    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型")
    private String orderType;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String addDate;

}
