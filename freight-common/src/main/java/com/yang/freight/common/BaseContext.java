package com.yang.freight.common;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/27
 * @Copyright：
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
