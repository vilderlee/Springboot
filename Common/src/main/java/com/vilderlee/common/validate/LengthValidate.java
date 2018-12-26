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
public class LengthValidate implements Validatable {
    @Override
    public boolean validate(Annotation annotation, Object o) throws ValidateException {
        if (o instanceof String) {
            try {
                int length = (int) annotation.annotationType().getField(RegexConstants.METHOD_VALUE).get(null);
                return ((String)o).length() == length;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
}
