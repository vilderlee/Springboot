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
 * VilderLee    2018/12/25      Create this file
 * </pre>
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Length {

    /**
     * length value
     *
     * @return
     */
    int value() default 0;

    /**
     * 字段描述
     *
     * @return
     */
    String description();

    String MESSAGE = "字段长度不符合条件";
}
