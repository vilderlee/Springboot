package com.vilderlee.common.validate;

import java.lang.annotation.Annotation;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/25      Create this file
 * </pre>
 */
public abstract class AbstractValidateFactory {

    /**
     * 获取验证器
     *
     * @param annotationName
     * @return
     */
    public abstract Validatable getValidate(String annotationName);

    /**
     * 判断目标对象是否符合验证器
     *
     * @param annotation
     * @param matchObject
     * @return
     */
    public abstract boolean match(Annotation annotation, Object matchObject);

}
