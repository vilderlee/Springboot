package com.vilderlee.sharding.jdbc.model;

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
public class OrdersDetail {
    /**
     * 订单明细id
     */
    private String id;

    /**
     * 订单id
     */
    private String ordersId;
    /**
     * 商品id
     */
    private String goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品属性
     */
    private String goodsKindname;
}
