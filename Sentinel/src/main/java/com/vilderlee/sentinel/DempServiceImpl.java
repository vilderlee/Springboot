package com.vilderlee.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.vilderlee.sentinel.config.ExceptionUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 类说明:
 *
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2019/5/28      Create this file
 * </pre>
 */
@Service
public class DempServiceImpl implements DempService {

    @Override @SentinelResource(value = "hello", fallback = "error" )
    public String hello(String s) {
        if (s.equals("123")) {
            throw new IllegalArgumentException("");
        }else {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return "123";
    }

    public String error(String s){
        return "Sentinel Error!!!!!!";
    }



    @Override
    @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = { ExceptionUtil.class})
    public String world(String s) {
        return  "调用get()" + s;
    }


}
