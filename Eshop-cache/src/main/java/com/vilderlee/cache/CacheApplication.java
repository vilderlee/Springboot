package com.vilderlee.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 重建缓存：比如我们的数据在缓存中都不存在了（LRU算法淘汰），这是我们应该从数据库中读取数据，然后写入缓存中
 * 分布式重建缓存：在不同的机器上，不同的服务实例中，去坐上面的事情，就会出现多个机器分布式重建去读取相同的数
 * 据，然后写入缓存中，这样的话分布式缓存重建就出现了并发冲突问题。
 * <p>
 * <p>
 * 如何解决：使用分布式锁，多个机器去写入同一个数据时前加锁。获取锁，比较与redis中的数据的更新时间，如果拿不到锁那么就等待。
 */

@SpringBootApplication
@ServletComponentScan("com.vilderlee.cache.listener")
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);

    }

}
