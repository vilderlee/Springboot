package com.vilderlee.tools.util;

/**
 * <pre>
 * Modify Information:
 * Author        Date          Description
 * ============ ============= ============================
 * VilderLee    2018/12/25      Create this file
 * </pre>
 */
public final class ArraysUtil {

    /**
     * 数组为空判断
     *
     * @param args
     * @return
     */
    public static boolean isNull(Object[] args) {
        if (0 == args.length || null == args) {
            return true;
        } else {
            return false;
        }
    }
}
