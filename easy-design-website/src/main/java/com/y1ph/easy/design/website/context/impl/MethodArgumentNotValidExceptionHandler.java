package com.y1ph.easy.design.website.context.impl;

import com.y1ph.easy.design.website.beans.ResultBean;
import com.y1ph.easy.design.website.constant.HttpStatus;
import com.y1ph.easy.design.website.context.ExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * 404异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class MethodArgumentNotValidExceptionHandler implements ExceptionHandler<Class<MethodArgumentNotValidException>> {
    @Override
    public <E extends Throwable> ResultBean<?> resolve(E exception) {
        return ResultBean.build(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @Override
    public Class<MethodArgumentNotValidException> getId() {
        return MethodArgumentNotValidException.class;
    }
}
