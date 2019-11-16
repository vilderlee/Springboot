package com.vilderlee.cache.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vilderlee.cache.listener.ApplicationContext;
import com.vilderlee.cache.model.ProductInfo;
import com.vilderlee.cache.model.ShopInfo;
import com.vilderlee.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * 功能描述:
 *
 * @package com.vilderlee.cache.kafka
 * @auther vilderlee
 * @date 2019/11/9 9:26 下午
 */
@Slf4j
public class KafkaMessageProcessor implements Runnable {

    private ConsumerRecord<String, String> consumerRecord;
    private CacheService cacheService;

    public KafkaMessageProcessor(ConsumerRecord<String, String> consumerRecord) {
        this.consumerRecord = consumerRecord;
        this.cacheService = ApplicationContext.getContext().getBean(CacheService.class);

    }

    @Override
    public void run() {
        String value = consumerRecord.value();

        JSONObject messageJSONObject = JSON.parseObject(value);
        String serviceId = messageJSONObject.getString("serviceId");
        if ("productInfoService".equals(serviceId)) {
            processProductInfoChangeMessage(messageJSONObject);
        } else if ("shopInfoService".equals(serviceId)) {
            processShopInfoChangeMessage(messageJSONObject);
        }
    }

    private void processProductInfoChangeMessage(JSONObject messageJSONObject) {
        Long productId = messageJSONObject.getLong("productId");
        //调用商品信息变更
        String productInfoJSON = "{\"id\": 1, \"name\": \"iphone7手机\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1}";
        ProductInfo productInfo = JSONObject.parseObject(productInfoJSON, ProductInfo.class);
        cacheService.saveProductInfo2LocalCache(productInfo);
        log.info("===================获取刚保存到本地缓存的商品信息：" + cacheService.getProductInfoFromLocalCache(productId));
        cacheService.saveProductInfo2RedisCache(productInfo);
    }


    /**
     * 处理店铺信息变更的消息
     *
     * @param messageJSONObject
     */
    private void processShopInfoChangeMessage(JSONObject messageJSONObject) {
        // 提取出商品id
        Long productId = messageJSONObject.getLong("productId");
        Long shopId = messageJSONObject.getLong("shopId");

        // 调用商品信息服务的接口
        // 直接用注释模拟：getProductInfo?productId=1，传递过去
        // 商品信息服务，一般来说就会去查询数据库，去获取productId=1的商品信息，然后返回回来

        // 龙果有分布式事务的课程，主要讲解的分布式事务几种解决方案，里面也涉及到了一些mq，或者其他的一些技术，但是那些技术都是浅浅的给你搭建一下，使用
        // 你从一个课程里，还是学到的是里面围绕的讲解的一些核心的知识
        // 缓存架构：高并发、高性能、海量数据，等场景

        String shopInfoJSON = "{\"id\": 1, \"name\": \"小王的手机店\", \"level\": 5, \"goodCommentRate\":0.99}";
        ShopInfo shopInfo = JSONObject.parseObject(shopInfoJSON, ShopInfo.class);
        cacheService.saveShopInfo2LocalCache(shopInfo);
        log.info("===================获取刚保存到本地缓存的店铺信息：" + cacheService.getShopInfoFromLocalCache(shopId));
        cacheService.saveShopInfo2RedisCache(shopInfo);
    }
}
