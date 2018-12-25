package com.vilderlee.gateway.validate;

import com.vilderlee.common.exception.ValidateException;
import com.vilderlee.common.validate.ValidateFactory;
import com.vilderlee.tools.util.ArraysUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/25      Create this file
 * </pre>
 */
@Component @Aspect public class ValidateAop {

    @Pointcut(value = "execution(* com.vilderlee.gateway.controller..*.*(..))") public void validate() {
    }

    /**
     * ProceedingJoinPoint is only supported for around advice
     * JoinPoint
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Before("validate()") private Object before(JoinPoint point) throws Throwable {
        Object object = point.getArgs()[0];
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            if (ArraysUtil.isNull(annotations)) {
                continue;
            }
            String fieldName = field.getName();
            fieldName = fieldName.replace(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
            Method method = object.getClass().getMethod("get" + fieldName);
            Object o = method.invoke(object);

            for (Annotation annotation : annotations) {
                try {
                    ValidateFactory.getInstance().match(annotation, o);
                } catch (ValidateException e) {
                    throw new ValidateException(e.getMessage());
                }
            }
        }
        return object;
    }
}
