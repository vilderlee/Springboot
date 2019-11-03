package com.vilderlee.eshopinventory.thread;

import com.vilderlee.eshopinventory.request.ProductInventoryCacheRefreshRequest;
import com.vilderlee.eshopinventory.request.ProductInventoryDBUpdateRequest;
import com.vilderlee.eshopinventory.request.Request;
import com.vilderlee.eshopinventory.request.RequestQueue;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.thread
 * @auther vilderlee
 * @date 2019/9/19 10:36 下午
 */
@Slf4j
public class RequestProcessThread implements Callable<Boolean> {

    public RequestProcessThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    private ArrayBlockingQueue<Request> queue;

    @Override
    public Boolean call() throws Exception {

        try {
            while (true) {
                //利用BlockingQueue的阻塞特性，如果队列内容为空，则阻塞
                Request request = queue.take();
                log.info("工作线程处理请求，商品id为{}", request.getProductId());
                boolean isForceRefresh = request.isForceRefresh();
                RequestQueue requestQueue = RequestQueue.getInstance();
                Map<Integer, Boolean> flagMap = requestQueue.getFlagMap();
                if (!isForceRefresh) {
                    if (request instanceof ProductInventoryDBUpdateRequest) {
                        flagMap.put(request.getProductId(), true);
                    } else if (request instanceof ProductInventoryCacheRefreshRequest) {
                        //刷新缓存请求，那么就去先判断是否存在，如果存在并且为true，就说明之前有一个这个商品的写请求
                        Boolean flag = flagMap.get(request.getProductId());
                        if (flag != null && flag) {
                            flagMap.put(request.getProductId(), false);
                        }
                        //如果flag为false,说明前面已经有一个数据库更新请求+一个缓存刷新请求
                        if (flag != null && !flag) {
                            return true;
                        }

                        if (flag == null) {
                            flagMap.put(request.getProductId(), false);
                        }
                    }
                }

                request.process();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
