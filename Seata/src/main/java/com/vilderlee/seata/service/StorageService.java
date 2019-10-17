package com.vilderlee.seata.service;

public interface StorageService {

    /**
     * deduct storage count
     */
    void deduct(String commodityCode, int count);
}