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
@Nullable(description = "日期")
public @interface Date {

    String pattern() default "yyyyMMdd";

    String MESSAGE = "字段日期格式不正确";
}
