package com.y1ph.easy.design.security.utils;

import com.y1ph.easy.design.security.beans.Principal;
import com.y1ph.easy.design.security.beans.SecurityContext;

/**
 * 权限工具类
 *
 * @author WFT
 * @since 2022/1/4
 */
public class SecurityUtil {

    /**
     * 私有化构造函数
     */
    private SecurityUtil() {

    }

    private final static ThreadLocal<SecurityContext> THREAD_LOCAL = new ThreadLocal<>();

    public static void clear() {
        THREAD_LOCAL.remove();
    }

    public static void set(SecurityContext context) {
        THREAD_LOCAL.set(context);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Principal<?>> T get() {
        return (T) getContext().getPrincipal();
    }

    public static SecurityContext getContext() {
        return THREAD_LOCAL.get();
    }
}
