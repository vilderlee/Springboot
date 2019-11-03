package com.vilderlee.sharding.jdbc.mapper.shard;

import com.vilderlee.sharding.jdbc.model.Orders;

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
public interface OrdersMapper {

    int saveOrder(Orders order);

    List<Orders> findAll();
}
