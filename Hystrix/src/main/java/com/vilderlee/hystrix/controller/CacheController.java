package com.vilderlee.hystrix.controller;

import com.netflix.hystrix.HystrixCommand;
import com.vilderlee.hystrix.GetProductServiceCommand;
import com.vilderlee.hystrix.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 功能描述:
 *
 * @package com.vilderlee.hystrix.controller
 * @auther vilderlee
 * @date 2019/11/19 11:33 下午
 */
@RestController
@Slf4j
public class CacheController {

    @GetMapping("/getProductInfo")
    public ProductInfo getProductInfo(Long productId){
        HystrixCommand<ProductInfo> hystrixCommand = new GetProductServiceCommand(productId);
        log.error("接收到的时间：{}", LocalDateTime.now());
        ProductInfo productInfo = hystrixCommand.execute();
        return productInfo;
    }

}
