package com.vilderlee.sharding.jdbc.mapper.shard;

import com.vilderlee.sharding.jdbc.model.Orders;

import java.util.List;
import org.apache.ibatis.annotations.Select;

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

    //    @Select("Select * FROM orders WHERE extra->> '$.username'= 'lichao' ")
//    @Select("Select * FROM orders WHERE id = 1 and JSON_CONTAINS('lichao', JSON_UNQUOTE(JSON_EXTRACT(extra,'$.username')) )")
    @Select("update orders SET extra = json_set(COALESCE(extra, '{}'), '$.k1', 456), extra = json_remove(extra, '$.notExist') WHERE id = 1")
    Orders find(String xxx);
}
