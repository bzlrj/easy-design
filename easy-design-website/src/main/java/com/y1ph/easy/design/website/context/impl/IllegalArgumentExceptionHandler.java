package com.y1ph.easy.design.website.context.impl;

import com.y1ph.easy.design.website.beans.ResultBean;
import com.y1ph.easy.design.website.constant.HttpStatus;
import com.y1ph.easy.design.website.context.ExceptionHandler;
import org.springframework.stereotype.Component;

/**
 * 非法参数异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class IllegalArgumentExceptionHandler implements ExceptionHandler<Class<IllegalArgumentException>> {

    @Override
    public <E extends Throwable> ResultBean<?> resolve(E exception) {
        return ResultBean.build(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @Override
    public Class<IllegalArgumentException> getId() {
        return IllegalArgumentException.class;
    }
}
