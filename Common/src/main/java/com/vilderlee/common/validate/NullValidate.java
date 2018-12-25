package com.vilderlee.common.validate;

import com.vilderlee.common.exception.ValidateException;
import com.vilderlee.common.util.ArraysUtil;
import com.vilderlee.common.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/25      Create this file
 * </pre>
 */
public class NullValidate implements Validatable {

    @Override
    public boolean validate(Annotation annotation, Object o) throws ValidateException {
        if (o instanceof String) {
            return !StringUtils.isEmpty((String) o);
        } else if (o instanceof List) {
            return !((List) o).isEmpty();
        } else if (o instanceof Map) {
            return !((Map) o).isEmpty();
        } else if (o.getClass().isArray()) {
            return !ArraysUtil.isNull((Object[]) o);
        } else {
            return null != o;
        }
    }
}
