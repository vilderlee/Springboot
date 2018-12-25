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

    protected static final String METHOD_DESCRIPTION = "description";
    protected static final String METHOD_PATTERN = "pattern";
    protected static final String FIELD_MESSAGE = "MESSAGE";
    protected static String RETURN_MSG  = "MESSAGE";
    protected static String RETURN_PATTERN = "Pattern is not Null!";


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
