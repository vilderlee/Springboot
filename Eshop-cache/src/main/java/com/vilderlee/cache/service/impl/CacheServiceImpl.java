package com.vilderlee.cache.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.vilderlee.cache.model.ProductInfo;
import com.vilderlee.cache.model.ShopInfo;
import com.vilderlee.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 功能描述:
 *
 * @package com.vilderlee.cache.service.impl
 * @auther vilderlee
 * @date 2019/11/9 5:07 下午
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String LOCAL_NAME = "local";


    @CachePut(value = LOCAL_NAME, key = "'key_' + #productInfo.getId()")
    @Override
    public ProductInfo saveLocalCache(ProductInfo productInfo) {
        return productInfo;
    }


    @Cacheable(value = LOCAL_NAME, key = "'key_' + #id")
    @Override
    public ProductInfo getLocalCache(Long id) {
        return null;
    }


    /**
     * 这里有一个重要的地方是我们存储Ehcache时，其实就是把实体类放在一个虚拟机中，所以返回值一定是数据值，不能为空。
     * 猜想，利用AOP将返回值直接放入。
     *
     * @param productInfo 商品信息
     * @return
     */
    @CachePut(value = LOCAL_NAME, key = "'product_info_' + #productInfo.getId()")
    @Override
    public ProductInfo saveProductInfo2LocalCache(ProductInfo productInfo) {
        return productInfo;
    }

    @Override
    public void saveProductInfo2RedisCache(ProductInfo productInfo) {
        String key = "product_info_" + productInfo.getId();
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(productInfo));
    }

    @CachePut(value = LOCAL_NAME, key = "'shop_info_' + #shopInfo.getId()")
    @Override
    public ShopInfo saveShopInfo2LocalCache(ShopInfo shopInfo) {
        return shopInfo;
    }

    @Override
    @CachePut(value = LOCAL_NAME, key = "'shop_info_' + #shopInfo.getId()")
    public ShopInfo saveShopInfo2RedisCache(ShopInfo shopInfo) {
        String key = "shopInfo_info_" + shopInfo.getId();
        redisTemplate.opsForValue().set(key, JSONObject.toJSONString(shopInfo));
        return shopInfo;
    }

    @Cacheable(value = LOCAL_NAME, key = "'shop_info_' + #shopId")
    @Override
    public ShopInfo getShopInfoFromLocalCache(Long shopId) {
        return null;
    }

    @Cacheable(value = LOCAL_NAME, key = "'product_info_' + #productId")
    @Override
    public ProductInfo getProductInfoFromLocalCache(Long productId) {
        return null;
    }

    @Override
    public ProductInfo getProductInfo2RedisCache(Long productId) {
        String key = "product_info_" + productId;
        return JSONObject.parseObject((String) redisTemplate.opsForValue().get(key), ProductInfo.class);
    }

    @Override
    public ShopInfo getShopInfo2RedisCache(Long shopId) {
        String key = "shopInfo_info_" + shopId;
        String value = (String) redisTemplate.opsForValue().get(key);
        return JSONObject.parseObject(value, ShopInfo.class);
    }
}
