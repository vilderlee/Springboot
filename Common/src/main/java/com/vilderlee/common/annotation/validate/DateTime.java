package com.vilderlee.common.annotation.validate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/26      Create this file
 * </pre>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Nullable(description = "时间")
public @interface DateTime {

    String pattern() default "yyyyMMdd HHmmss";

    String MESSAGE = "字段时间格式不正确";
}
