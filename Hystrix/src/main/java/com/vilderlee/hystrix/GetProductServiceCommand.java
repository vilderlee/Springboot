package com.vilderlee.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.vilderlee.hystrix.model.ProductInfo;

/**
 * 类说明: 线程池隔离
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/10/15      Create this file
 * </pre>
 */
public class GetProductServiceCommand extends HystrixCommand<ProductInfo> {

    private Long productId;

    public GetProductServiceCommand(Long productId) {
        super(setter());
        this.productId = productId;
    }

    private static Setter setter() {
        //服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("ProductInfoGroup");

        //服务标识
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("getProduct");

        //线程池名称
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("Product-pool");

        //线程池配置
        HystrixThreadPoolProperties.Setter poolProperties = HystrixThreadPoolProperties.Setter()
                .withCoreSize(10)
                .withKeepAliveTimeMinutes(10)
                .withMaxQueueSize(Integer.MAX_VALUE)
                .withQueueSizeRejectionThreshold(10000);

        //命令属性配置
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                .withExecutionTimeoutInMilliseconds(10000);

        return HystrixCommand.Setter.withGroupKey(groupKey)
                .andCommandKey(commandKey)
                .andThreadPoolKey(threadPoolKey)
                .andThreadPoolPropertiesDefaults(poolProperties)
                .andCommandPropertiesDefaults(commandProperties);

    }

    @Override
    protected ProductInfo run() throws Exception {
        ProductService productService = new ProductService();
        return productService.getStock(productId);
    }
}
