package com.y1ph.easy.design.website.context;

import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * 拦截器公共接口
 *
 * @author WFT
 * @since 2022/1/1
 */
public interface BaseHandlerInterceptor extends HandlerInterceptor {
    /**
     * 路径匹配规则
     *
     * @return {@link List}
     */
    List<String> getPathPatterns();

}
