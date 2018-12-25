package com.vilderlee.common.validate;

import com.vilderlee.common.annotation.validate.Length;
import com.vilderlee.common.annotation.validate.Mobile;
import com.vilderlee.common.annotation.validate.Nullable;
import com.vilderlee.common.annotation.validate.Pattern;
import com.vilderlee.common.exception.ValidateException;
import com.vilderlee.common.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/25      Create this file
 * </pre>
 */
public class ValidateFactory extends AbstractValidateFactory {

    private static Map<String, Object> map = new HashMap<>();
    private static ValidateFactory validateFactory;
    private static Object monitor = new Object();

    /**
     *
     * 初始化验证器
     *
     */
    static {
        map.put(Nullable.class.getSimpleName(), new NullValidate());
        map.put(Length.class.getSimpleName(), new NullValidate());
        map.put(Mobile.class.getSimpleName(), new NullValidate());
        map.put(Pattern.class.getSimpleName(), new NullValidate());
    }

    private ValidateFactory() {
    }

    public static ValidateFactory getInstance() {
        synchronized (monitor) {
            if (null == validateFactory) {
                synchronized (monitor) {
                    validateFactory = new ValidateFactory();
                }
            }
        }
        return validateFactory;
    }

    /**
     * 获取对应的验证器
     *
     * @param annotationName
     * @return
     */
    @Override public Validatable getValidate(String annotationName) {
        return (Validatable) map.get(annotationName);
    }

    /**
     * 通过验证器校验数据
     *
     * @param annotation
     * @param matchObject
     * @return
     */
    @Override public boolean match(Annotation annotation, Object matchObject) {
        boolean isMatch = false;
        try {

            Validatable validatable = getValidate(annotation.annotationType().getSimpleName());
            isMatch = validatable.validate(annotation, matchObject);

            String description = "";
            Method method = annotation.annotationType().getMethod(METHOD_DESCRIPTION);
            if (null != method) {
                description = (String) method.invoke(annotation, null);
            }
            Field field = annotation.annotationType().getField(FIELD_MESSAGE);
            if (!StringUtils.isEmpty((String) field.get(null))) {
                RETURN_MSG = description + field.get(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isMatch) {
            throw new ValidateException(RETURN_MSG);
        }
        return true;
    }
}
