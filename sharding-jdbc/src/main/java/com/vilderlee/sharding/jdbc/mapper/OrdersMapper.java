package com.vilderlee.sharding.jdbc.mapper;

import com.vilderlee.sharding.jdbc.model.Orders;

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

}
