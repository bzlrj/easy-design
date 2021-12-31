package com.y1ph.easy.design.website.context.impl;

import org.springframework.stereotype.Component;

/**
 * 非法参数异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class IllegalArgumentExceptionHandler extends BaseParameterExceptionHandler<Class<IllegalArgumentException>> {

    @Override
    public Class<IllegalArgumentException> getId() {
        return IllegalArgumentException.class;
    }
}
