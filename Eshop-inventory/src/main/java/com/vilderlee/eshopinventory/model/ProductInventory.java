package com.vilderlee.eshopinventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.model
 * @auther vilderlee
 * @date 2019/9/19 11:03 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {
    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 库存数量
     */
    private Long inventoryCnt;
}
