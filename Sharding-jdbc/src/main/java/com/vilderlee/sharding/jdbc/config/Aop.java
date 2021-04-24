package com.vilderlee.sharding.jdbc.config;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * Aop
 *
 * @ClassName Aop
 * @Description
 * @Author VilderLee
 * @Date 2021/4/8 11:27 上午
 */

@Component
@Aspect
public class Aop {


    @Before(value = "@annotation(org.springframework.transaction.annotation.Transactional)")
    public void before(JoinPoint joinPoint) {
        final String name = joinPoint.getTarget().getClass().getPackage().getName();
        if (name.contains("com.vilderlee")) {
            throw new RuntimeException("xxxxx!");
        }

    }
}
