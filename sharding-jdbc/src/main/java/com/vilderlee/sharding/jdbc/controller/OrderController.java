package com.vilderlee.sharding.jdbc.controller;

import com.vilderlee.sharding.jdbc.model.Orders;
import groovy.util.logging.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api("订单相关")
@RequestMapping("/order")
public class OrderController {




    @PostMapping("/")
    @ApiOperation("新增订单")
    @ApiImplicitParams(
            {}
    )
    public boolean save(Orders order){

    }

}
