package com.vilderlee.cache.controller;

import com.alibaba.fastjson.JSONObject;
import com.vilderlee.cache.model.ProductInfo;
import com.vilderlee.cache.model.ShopInfo;
import com.vilderlee.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @package com.vilderlee.controller
 * @auther vilderlee
 * @date 2019/11/9 5:12 下午
 */
@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @Value("spring.redis.cluster.nodes")
    private List<String> list;

    @RequestMapping("/testPutCache")
    public String testPutCache(ShopInfo productInfo) {
        cacheService.saveShopInfo2RedisCache(productInfo);
        return "success";
    }

    @RequestMapping("/testPutCache2")
    public String testPutCache2(ShopInfo productInfo) {
        cacheService.saveShopInfo2LocalCache(productInfo);
        return "success";
    }


    @RequestMapping("/getShopInfo")
    public ShopInfo getCache(@RequestParam(value = "shopId") Long id) {
        return cacheService.getShopInfo2RedisCache(id);
    }

    @RequestMapping("/getProductInfo")
    public ProductInfo getProductInfo(@RequestParam(value = "productId") Long id) {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProductInfo productInfo = cacheService.getProductInfoFromLocalCache(id);
        System.out.println(productInfo);
        return cacheService.getProductInfo2RedisCache(id);
    }

    @RequestMapping("/saveProduct")
    public void saveProduct(ProductInfo productInfo) {
        cacheService.saveProductInfo2RedisCache(productInfo);
        cacheService.saveProductInfo2LocalCache(productInfo);
    }
}
