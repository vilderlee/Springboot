package com.vilderlee.eshopinventory.request;

import com.vilderlee.eshopinventory.model.ProductInventory;
import com.vilderlee.eshopinventory.service.ProductInventoryService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *  如果一个商品发生了交易，修改该商品库存
 *  此时请求发过来就要去修改库存 （1）删除缓存，（2）修改库存，更新数据库
 *
 * @package com.vilderlee.eshopinventory.request
 * @auther vilderlee
 * @date 2019/9/19 10:54 下午
 */
@Slf4j
public class ProductInventoryDBUpdateRequest implements Request {

    /**
     * 库存
     */
    private ProductInventory productInventory;
    /**
     * 库存服务
     */
    private ProductInventoryService productInventoryService;

    public ProductInventoryDBUpdateRequest(ProductInventory productInventory, ProductInventoryService productInventoryService) {
        this.productInventory = productInventory;
        this.productInventoryService = productInventoryService;
    }

    @Override
    public void process() {
        log.info("数据库更新请求开始执行：商品Id为{}，库存为{}", productInventory.getProductId(),productInventory.getInventoryCnt());
        //删除缓存
        productInventoryService.removeProductInventoryCache(productInventory);
        log.info("已删除redis中的缓存");
        //模拟卡顿
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //修改数据库中的库存
        productInventoryService.updateProductInventory(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productInventory.getProductId();
    }

    @Override
    public boolean isForceRefresh() {
        return false;
    }
}
