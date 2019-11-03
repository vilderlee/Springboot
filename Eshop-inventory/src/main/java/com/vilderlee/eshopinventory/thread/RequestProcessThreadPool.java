package com.vilderlee.eshopinventory.thread;

import com.vilderlee.eshopinventory.request.Request;
import com.vilderlee.eshopinventory.request.RequestQueue;

import java.util.concurrent.*;

/**
 * 功能描述:
 * 线程池 单例
 *
 * @package com.vilderlee.eshopinventory.threadpool
 * @auther vilderlee
 * @date 2019/9/19 10:26 下午
 */
public class RequestProcessThreadPool {

    /**
     * 线程池
     */
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);


    public RequestProcessThreadPool() {
        RequestQueue requestQueue = RequestQueue.getInstance();

        for (int i = 0; i < 8; i++) {
            ArrayBlockingQueue<Request> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
            requestQueue.addQueue(arrayBlockingQueue);
            threadPool.submit(new RequestProcessThread(arrayBlockingQueue));
        }
    }

    private static class Singleton {
        private static RequestProcessThreadPool instance;

        static {
            instance = new RequestProcessThreadPool();
        }

        public static RequestProcessThreadPool getInstance() {
            return instance;
        }
    }


    public static RequestProcessThreadPool getInstance() {
        return Singleton.getInstance();
    }

    public static void init(){
        getInstance();
    }
}
