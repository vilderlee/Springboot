package com.vilderlee.userservice.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * Modify Information:
 * Author       Date           Description
 * ============ ============= ============================
 * dell      2018/10/29        TODO
 * </pre>
 */
@Component
@Aspect
public class DubboLog {
    private Log log = LogFactory.getLog(this.getClass());

    /**
     *
     *  execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)  throws-pattern?)
     * 　　　　ret-type-pattern,name-pattern(param-pattern)是必须的.
     * 　　　　ret-type-pattern:标识方法的返回值，需要使用全路径的类名如java.lang.String,也可以为*表示任何返回值；
     * 　　　　name-pattern:指定方法名,*代表所有,例如set*,代表以set开头的所有方法.
     * 　　　　param-pattern:指定方法参数(声明的类型),(..)代表所有参数,(*)代表一个参数,(*,String)代表第一个参数为任何值,第二个为String类型.
     */
    @Pointcut(value = "execution(* com.springboot.provider.service..*ServiceImpl.tx*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    private Object around(ProceedingJoinPoint point) throws Throwable {
        Object object = point.getArgs()[0];
        log.info("dubbo接收到的数据为---->" + object.toString());
        Object result = point.proceed();
        log.info("dubb返回的数据为---->" + result.toString());
        return result;
    }
}
