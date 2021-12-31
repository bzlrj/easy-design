package com.y1ph.easy.design.website.context.impl;

import com.y1ph.easy.design.website.beans.ResultBean;
import com.y1ph.easy.design.website.constant.HttpStatus;
import com.y1ph.easy.design.website.context.ExceptionHandler;
import org.springframework.stereotype.Component;

/**
 * 未知异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class UnknownExceptionHandler implements ExceptionHandler<Class<Exception>> {

    @Override
    public <E extends Throwable> ResultBean<?> resolve(E exception) {
        return ResultBean.build(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @Override
    public Class<Exception> getId() {
        return Exception.class;
    }
}
