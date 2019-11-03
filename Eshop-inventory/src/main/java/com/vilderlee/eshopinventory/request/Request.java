package com.vilderlee.eshopinventory.request;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.threadpool
 * @auther vilderlee
 * @date 2019/9/19 10:35 下午
 */
public interface Request {

    void process();

    Integer getProductId();

    boolean isForceRefresh();
}
