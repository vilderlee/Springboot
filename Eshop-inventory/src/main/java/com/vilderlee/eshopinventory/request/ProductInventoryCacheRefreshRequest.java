package com.vilderlee.eshopinventory.request;

import com.vilderlee.eshopinventory.model.ProductInventory;
import com.vilderlee.eshopinventory.service.ProductInventoryService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.request
 * @auther vilderlee
 * @date 2019/9/19 11:33 下午
 */
@Slf4j
public class ProductInventoryCacheRefreshRequest implements Request {
    /**
     * 库存
     */
    private Integer productId;
    /**
     * 库存服务
     */
    private ProductInventoryService productInventoryService;

    /**
     *
     * 是否强制刷新缓存
     * @param productId
     * @param productInventoryService
     */
    @Getter
    private boolean forceRefresh;

    public ProductInventoryCacheRefreshRequest(Integer productId, ProductInventoryService productInventoryService, boolean isForceRefresh) {
        this.productId = productId;
        this.productInventoryService = productInventoryService;
        this.forceRefresh = isForceRefresh;
    }

    @Override
    public void process() {
        ProductInventory productInventory = productInventoryService.findProductInventory(productId);
        log.info("已查到最新的商品库存数量，商品Id为{}，库存数量为{}", productInventory.getProductId(),productInventory.getInventoryCnt());
        productInventoryService.setProductInventoryCache(productInventory);
    }

    @Override
    public Integer getProductId() {
        return productId;
    }
}
