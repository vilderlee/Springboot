package com.vilderlee.cache.service;

import com.vilderlee.cache.model.ProductInfo;
import com.vilderlee.cache.model.ShopInfo;

/**
 * 功能描述:
 *
 * @package com.vilderlee.cache.service
 * @auther vilderlee
 * @date 2019/11/9 5:06 下午
 */
public interface CacheService {

    /**
     * 将商品信息保存到本地缓存中
     *
     * @param productInfo 商品信息
     * @return 商品信息
     */
    ProductInfo saveLocalCache(ProductInfo productInfo);

    /**
     * 从本地缓存中获取商品信息
     *
     * @param id
     * @return
     */
    ProductInfo getLocalCache(Long id);

    /**
     * 将商品信息保存到本地缓存ehcache中
     *
     * @param productInfo 商品信息
     */
    ProductInfo saveProductInfo2LocalCache(ProductInfo productInfo);


    /**
     * 存储到Redis中
     *
     * @param productInfo
     */
    void saveProductInfo2RedisCache(ProductInfo productInfo);

    /**
     * 将商品信息保存到本地缓存ehcache中
     *
     * @param shopInfo 商店信息
     */
    ShopInfo saveShopInfo2LocalCache(ShopInfo shopInfo);


    /**
     * 存储到Redis中
     *
     * @param shopInfo
     */
    ShopInfo saveShopInfo2RedisCache(ShopInfo shopInfo);


    ShopInfo getShopInfoFromLocalCache(Long shopId);

    ProductInfo getProductInfoFromLocalCache(Long productId);

    ProductInfo getProductInfo2RedisCache(Long productId);

    ShopInfo getShopInfo2RedisCache(Long shopId);
}
