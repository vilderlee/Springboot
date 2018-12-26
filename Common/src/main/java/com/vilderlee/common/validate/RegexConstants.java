package com.vilderlee.common.validate;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/25      Create this file
 * </pre>
 */
public final class RegexConstants {

    public static final String METHOD_DESCRIPTION = "description";
    public static final String METHOD_PATTERN = "pattern";
    public static final String FIELD_MESSAGE = "MESSAGE";
    public static final String METHOD_VALUE = "value";
    public static String RETURN_MSG  = "MESSAGE";
    public static String RETURN_CODE  = "1000";

    public static String RETURN_PATTERN = "Pattern is not Null!";

    public static final String MOBILE_REGEX = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$";
}
