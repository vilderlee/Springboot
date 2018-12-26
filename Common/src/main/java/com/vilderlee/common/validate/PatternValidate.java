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
    public boolean validate(Annotation annotation, Object o) throws ValidateException {
        if (o instanceof String) {
            try {
                String pattern = (String) annotation.annotationType().getField(RegexConstants.METHOD_PATTERN).get(null);
                return ((String) o).matches(pattern);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
