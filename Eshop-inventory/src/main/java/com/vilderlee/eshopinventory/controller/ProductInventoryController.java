package com.vilderlee.eshopinventory.controller;

import com.vilderlee.eshopinventory.model.ProductInventory;
import com.vilderlee.eshopinventory.request.ProductInventoryCacheRefreshRequest;
import com.vilderlee.eshopinventory.request.ProductInventoryDBUpdateRequest;
import com.vilderlee.eshopinventory.request.Request;
import com.vilderlee.eshopinventory.service.ProductInventoryService;
import com.vilderlee.eshopinventory.service.RequestAsyncProcessService;
import com.vilderlee.eshopinventory.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.controller
 * @auther vilderlee
 * @date 2019/11/3 12:22 下午
 *
 * 要模拟的场景：
 *  1。一个更新商品库存的请求过来，然后此时会删除redis的缓存，然后模拟卡顿5s
 *  2。在这个卡顿5s内，我们发送一个商品缓存的读请求，因为此时redis没有缓存，就会来请求将数据库中最新的值刷新到缓存中
 *  3。此时读请求会路由到同一个内存队列中阻塞中，不会执行
 *  4。等5s中过后，写请求完成来数据库的更新之后，读请求才会执行
 *  5。读请求执行的时候，会将最新的库存从数据库中查询出来，然后更新到缓存中
 *
 * 如果不一致情况，可能会出现redis中还是100，数据库是99
 *
 */
@RestController
@Slf4j
public class ProductInventoryController {

    @Autowired
    private RequestAsyncProcessService requestAsyncProcessService;

    @Autowired
    private ProductInventoryService productInventoryService;

    /**
     * 更新商品库存
     */
    @RequestMapping("/updateProductInventory")
    public Response updateProductInventory(ProductInventory productInventory) {
        log.info("接收到更新商品库存的请求，商品Id为{}，库存数量为{}", productInventory.getProductId(), productInventory.getInventoryCnt());

        Response response = null;
        try {
            Request request = new ProductInventoryDBUpdateRequest(productInventory, productInventoryService);
            requestAsyncProcessService.process(request);
            response = new Response(Response.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(Response.FAILURE);
        }
        return response;
    }


    @GetMapping("/getProductInventory")
    public ProductInventory getProductInventory(Integer productId) {
        ProductInventory productInventory = null;
        long waitTime = 0L;
        long startTime =0L;
        long endTime = 0L;
        try {

            Request request = new ProductInventoryCacheRefreshRequest(productId, productInventoryService, false);
            requestAsyncProcessService.process(request);

            //将请求扔给service异步操作后，需要while（true）一会儿，在这里hang住，
            //去尝试等待前面有商品库存更新的操作，同时缓存刷新的操作，将最新的数据刷新到缓存中


            //超过200ms没有获取到结果，直接从数据库中获取数据
            while (true) {
                if (waitTime > 200) {
                    break;
                }
                startTime = System.currentTimeMillis();
                productInventory = productInventoryService.getProductInventory(productId);

                if (productInventory != null) {
                    return productInventory;
                } else {
                    //如果没有读取到结果，那么等待一段时间
                    TimeUnit.MICROSECONDS.sleep(20);
                    endTime = System.currentTimeMillis();
                    waitTime = endTime - startTime;
                }
            }
            productInventory = productInventoryService.findProductInventory(productId);
            if (productInventory != null) {
                request = new ProductInventoryCacheRefreshRequest(productId,productInventoryService,true);
                requestAsyncProcessService.process(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productInventory;

    }

}
