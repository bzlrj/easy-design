package com.y1ph.easy.design.website.context.impl;

import com.y1ph.easy.design.website.beans.ResultBean;
import com.y1ph.easy.design.website.constant.HttpStatus;
import com.y1ph.easy.design.website.context.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

/**
 * Http消息不可读异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class HttpMessageNotReadableExceptionHandler implements ExceptionHandler<Class<HttpMessageNotReadableException>> {

    @Override
    public <E extends Throwable> ResultBean<?> resolve(E exception) {
        return ResultBean.build(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @Override
    public Class<HttpMessageNotReadableException> getId() {
        return HttpMessageNotReadableException.class;
    }
}
