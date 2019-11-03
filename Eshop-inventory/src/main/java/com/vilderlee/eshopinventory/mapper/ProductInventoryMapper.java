package com.vilderlee.eshopinventory.mapper;

import com.vilderlee.eshopinventory.model.ProductInventory;
import org.springframework.stereotype.Repository;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.dao
 * @auther vilderlee
 * @date 2019/9/19 11:00 下午
 */
@Repository
public interface ProductInventoryMapper {

    /**
     * 更新库存
     */
    void updateInventoryCntDao(ProductInventory productInventory);

    /**
     * 根据商品ID查找商品库存
     * @param productId
     * @return
     */
    ProductInventory findProductInventory(Integer productId);
}
