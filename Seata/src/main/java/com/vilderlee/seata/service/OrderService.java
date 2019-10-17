package com.vilderlee.seata.service;

public interface OrderService {

    /**
     * create order
     */
    Order create(String userId, String commodityCode, int orderCount);
}