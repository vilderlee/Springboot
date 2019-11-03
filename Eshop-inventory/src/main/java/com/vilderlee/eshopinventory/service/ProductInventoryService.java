package com.vilderlee.eshopinventory.service;

import com.vilderlee.eshopinventory.model.ProductInventory;

/**
 * 功能描述:    商品库存
 *
 * @package com.vilderlee.eshopinventory.service
 * @auther vilderlee
 * @date 2019/9/19 11:09 下午
 */
public interface ProductInventoryService {

    /**
     * 更新商品库存
     * @param productInventory 商品
     */
    void updateProductInventory(ProductInventory productInventory);

    /**
     * 删除商品库存
     * @param productInventory 商品
     */
    void removeProductInventoryCache(ProductInventory productInventory);

    /**
     * 根据商品ID查找库存
     * @param productId 商品ID
     * @return 商品
     */
    ProductInventory findProductInventory(Integer productId);

    /**
     * 设置缓存
     * @param productInventory 商品
     */
    void setProductInventoryCache(ProductInventory productInventory);


    ProductInventory getProductInventory(Integer productId);
}
