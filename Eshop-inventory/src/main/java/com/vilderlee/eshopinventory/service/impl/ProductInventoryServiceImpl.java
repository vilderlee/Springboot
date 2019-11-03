package com.vilderlee.eshopinventory.service.impl;

import com.vilderlee.eshopinventory.mapper.ProductInventoryMapper;
import com.vilderlee.eshopinventory.model.ProductInventory;
import com.vilderlee.eshopinventory.redis.RedisDAO;
import com.vilderlee.eshopinventory.service.ProductInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 功能描述: 商品库存实现类
 *
 * @package com.vilderlee.eshopinventory.service.impl
 * @auther vilderlee
 * @date 2019/9/19 11:13 下午
 */
@Service("productInventoryService")
@Slf4j
public class ProductInventoryServiceImpl implements ProductInventoryService {

    @Autowired
    private ProductInventoryMapper productInventoryDAO;

    @Autowired
    private RedisDAO redisDAO;

    @Override
    public void updateProductInventory(ProductInventory productInventory) {
        productInventoryDAO.updateInventoryCntDao(productInventory);
        log.info("已修改数据库的库存，商品ID为{}，库存数据为{}", productInventory.getProductId(), productInventory.getInventoryCnt());
    }

    @Override
    public void removeProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        log.info("删除redis的缓存，key为{}", key);
        redisDAO.delete(key);
        log.error("获取：{}" , redisDAO.get(key));
    }

    @Override
    public ProductInventory findProductInventory(Integer productId) {
        return productInventoryDAO.findProductInventory(productId);
    }

    @Override
    public void setProductInventoryCache(ProductInventory productInventory) {
        String key = "product:inventory:" + productInventory.getProductId();
        redisDAO.set(key, String.valueOf(productInventory.getInventoryCnt()));
        log.info("已更新商品库存的缓存，商品Id为{}，商品数量为{}，key为{}", productInventory.getProductId(),productInventory.getInventoryCnt(),key);
    }

    /**
     * 获取商品库存的缓存
     * @param productId
     * @return
     */
    @Override
    public ProductInventory getProductInventory(Integer productId) {
        Long inventoryCnt = 0L;
        String key = "product:inventory:" + productId;
        String result = redisDAO.get(key);
        if (!StringUtils.isEmpty(result)){
            try {
                inventoryCnt = Long.valueOf(result);
                return  new ProductInventory(productId, inventoryCnt);
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
