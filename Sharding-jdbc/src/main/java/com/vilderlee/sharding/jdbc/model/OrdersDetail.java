package com.vilderlee.sharding.jdbc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("订单详情")
public class OrdersDetail {
    /**
     * 订单明细id
     */
    @ApiModelProperty(value = "订单明细id")
    private String id;
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private String ordersId;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private String goodsId;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    /**
     * 商品属性
     */
    @ApiModelProperty(value = "商品属性")
    private String goodsKindName;
}
