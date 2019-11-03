package com.vilderlee.eshopinventory.request;


import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.request
 * @auther vilderlee
 * @date 2019/9/19 10:43 下午
 */
public class RequestQueue {

    /**
     * 内存队列
     */
    private ArrayList<ArrayBlockingQueue<Request>> queues = new ArrayList<>();

    /**
     *
     */
    private Map<Integer,Boolean> flagMap = new ConcurrentHashMap<>();


    private static class Singleton {
        private static RequestQueue instance;

        static {
            instance = new RequestQueue();
        }

        public static RequestQueue getInstance() {
            return instance;
        }
    }

    /**
     * 生产一个单例
     * @return
     */
    public static RequestQueue getInstance() {
        return RequestQueue.Singleton.getInstance();
    }

    /***
     * 添加一个内部队列
     *
     * @param blockingQueue
     */
    public void addQueue(ArrayBlockingQueue<Request> blockingQueue){
        queues.add(blockingQueue);
    }

    /**
     * 获取队列大小
     * @return
     */
    public int getQueueSize(){
        return queues.size();
    }

    /**
     * 获取队列
     *
     * @param index 下标
     * @return
     */
    public ArrayBlockingQueue<Request> getQueue(int index){
        return queues.get(index);
    }

    public Map<Integer, Boolean> getFlagMap() {
        return flagMap;
    }
}
