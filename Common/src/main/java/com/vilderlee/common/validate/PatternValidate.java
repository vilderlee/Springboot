package com.vilderlee.common.validate;

import com.vilderlee.common.exception.ValidateException;

import java.lang.annotation.Annotation;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/25      Create this file
 * </pre>
 */
public class PatternValidate implements Validatable {
    @Override
    public boolean validate(Annotation annotation,Object o) throws ValidateException {
        return false;
    }
}
