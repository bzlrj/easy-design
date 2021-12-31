package com.y1ph.easy.design.website.context.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingRequestHeaderException;

/**
 * 缺少请求头异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class MissingRequestHeaderExceptionHandler extends BaseParameterExceptionHandler<Class<MissingRequestHeaderException>> {

    @Override
    public Class<MissingRequestHeaderException> getId() {
        return MissingRequestHeaderException.class;
    }
}
