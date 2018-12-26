package com.vilderlee.common.exception;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/25      Create this file
 * </pre>
 */
public class ValidateException extends RuntimeException {

    private String code;

    public ValidateException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    protected ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getCode() {
        return code;
    }
}
