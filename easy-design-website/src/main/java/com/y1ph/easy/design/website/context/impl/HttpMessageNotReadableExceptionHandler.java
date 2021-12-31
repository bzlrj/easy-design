package com.y1ph.easy.design.website.context.impl;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

/**
 * Http消息不可读异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class HttpMessageNotReadableExceptionHandler extends BaseParameterExceptionHandler<Class<HttpMessageNotReadableException>> {

    @Override
    public Class<HttpMessageNotReadableException> getId() {
        return HttpMessageNotReadableException.class;
    }
}
