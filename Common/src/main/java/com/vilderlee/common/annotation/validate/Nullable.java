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
@Target({ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Nullable {

    boolean value() default false;
    /**
     * 字段描述
     *
     * @return
     */
    String description();

    String MESSAGE = "字段不能为空";
}
