package com.vilderlee.cache.zk;

import java.util.concurrent.CountDownLatch;

/**
 * 功能描述:
 *
 * @package com.vilderlee.cache.zk
 * @auther vilderlee
 * @date 2019/11/14 11:12 下午
 */
public class ZooKeeperSession {

    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);


    private ZooKeeperSession() {

    }

    private static class Singleton {

        private static ZooKeeperSession zooKeeperSession;

        static {
            zooKeeperSession = new ZooKeeperSession();
        }

        private static ZooKeeperSession getInstance() {
            return zooKeeperSession;
        }
    }

    public static ZooKeeperSession getInstance() {
        return ZooKeeperSession.Singleton.getInstance();
    }

}
