package com.vilderlee.cache.listener;

/**
 * 功能描述:
 *
 * @package com.vilderlee.cache.listener
 * @auther vilderlee
 * @date 2019/11/9 10:16 下午
 */
public class ApplicationContext {
    private static org.springframework.context.ApplicationContext context;

    public static org.springframework.context.ApplicationContext getContext() {
        return context;
    }

    public static void setContext(org.springframework.context.ApplicationContext context) {
        ApplicationContext.context = context;
    }
}
