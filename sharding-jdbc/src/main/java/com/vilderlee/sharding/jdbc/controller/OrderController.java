package com.vilderlee.sharding.jdbc.controller;

import com.vilderlee.sharding.jdbc.mapper.noshard.ShardConfigMapper;
import com.vilderlee.sharding.jdbc.mapper.shard.OrdersMapper;
import com.vilderlee.sharding.jdbc.model.Orders;
import com.vilderlee.sharding.jdbc.model.ShardConfig;
import groovy.util.logging.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@Slf4j
@Api(tags = "订单相关")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ShardConfigMapper shardConfigMapper;
    @PostMapping("/")
    @ApiOperation("新增订单")
//    @ApiImplicitParam(name = "order", value = "用户订单", required = true, paramType = "body",
//                    dataType = "Orders")
    public boolean save(Orders order){
        int result = ordersMapper.saveOrder(order);
        if (result!=1){
            return false;
        }else {
            return true;
        }
    }


    @GetMapping("/findAll")
    @ApiOperation("查询所有订单")
    public List<Orders> findAll(){
        return ordersMapper.findAll();
    }


    @PostMapping("/update")
    @ApiOperation(value = "更改并修改订单",notes = "证明事务可行")
    @Transactional
    public String update(Orders orders){
        ShardConfig shardConfig = new ShardConfig("2010","0001");
        ordersMapper.saveOrder(orders);
        shardConfigMapper.save(shardConfig);
        return "success";
    }
}
