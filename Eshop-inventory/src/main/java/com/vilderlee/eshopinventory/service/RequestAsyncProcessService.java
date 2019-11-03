package com.vilderlee.eshopinventory.service;

import com.vilderlee.eshopinventory.request.Request;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.service
 * @auther vilderlee
 * @date 2019/11/3 12:02 下午
 */
public interface RequestAsyncProcessService {

    void process(Request request);
}
