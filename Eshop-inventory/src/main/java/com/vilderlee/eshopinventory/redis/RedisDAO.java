package com.vilderlee.eshopinventory.redis;

import org.springframework.stereotype.Repository;

/**
 * 功能描述:
 *
 * @package com.vilderlee.eshopinventory.dao
 * @auther vilderlee
 * @date 2019/9/18 9:49 下午
 */
public interface RedisDAO {

    void set(String key,String value);

    String get(String key);
}
