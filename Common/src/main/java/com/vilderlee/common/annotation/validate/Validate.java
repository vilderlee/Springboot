package com.vilderlee.common.annotation.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @Target: 表示注解的作用域
 * @Retention: 表示注解的运行范围
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/25      Create this file
 * </pre>
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)

public @interface Validate {
    String value() default "";
}
