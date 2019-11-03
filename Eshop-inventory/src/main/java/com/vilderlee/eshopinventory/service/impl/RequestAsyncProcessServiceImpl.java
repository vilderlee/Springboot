package com.vilderlee.eshopinventory.service.impl;

import com.vilderlee.eshopinventory.request.ProductInventoryCacheRefreshRequest;
import com.vilderlee.eshopinventory.request.ProductInventoryDBUpdateRequest;
import com.vilderlee.eshopinventory.request.Request;
import com.vilderlee.eshopinventory.request.RequestQueue;
import com.vilderlee.eshopinventory.service.RequestAsyncProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 功能描述: 请求异步处理的Service实现
 *
 * @package com.vilderlee.eshopinventory.service.impl
 * @auther vilderlee
 * @date 2019/11/3 12:04 下午
 */
@Service("requestAsyncProcessServiceImpl")
@Slf4j
public class RequestAsyncProcessServiceImpl implements RequestAsyncProcessService {
    @Override
    public void process(Request request) {

        //请求的路由，根据每个请求的商品Id，路由到对象的内存队列中
        ArrayBlockingQueue<Request> queue = getRoutingQueue(request.getProductId());
        //将请求放入对应的队列中
        try {
            queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private ArrayBlockingQueue<Request> getRoutingQueue(Integer productId) {

        RequestQueue requestQueue = RequestQueue.getInstance();
        //获取商品IDHash值
        String key = String.valueOf(productId);
        int h;
        int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        //对hash值取模，将Hash值路由到指定的内存队列中，比如内存队列大小8，取值在0～7
        int index = (requestQueue.getQueueSize() - 1) & hash;
        log.info("路由内存队列，商品Id为{},队列索引为{}",productId, index);
        return requestQueue.getQueue(index);
    }
}
