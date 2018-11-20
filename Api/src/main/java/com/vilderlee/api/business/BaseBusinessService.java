package com.vilderlee.api.business;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/11/12      Create this file
 * </pre>
 */
public abstract class BaseBusinessService<Request,Response> {
    protected abstract Response dobusiness(Request BaseRequest);
}
