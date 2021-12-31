package com.y1ph.easy.design.website.context.impl;

import com.y1ph.easy.design.website.beans.ResultBean;
import com.y1ph.easy.design.website.constant.HttpStatus;
import com.y1ph.easy.design.website.context.ExceptionHandler;

/**
 * 参数异常公共处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
public abstract class BaseParameterExceptionHandler<T extends Class<? extends Throwable>> implements ExceptionHandler<T> {

    @Override
    public <E extends Throwable> ResultBean<?> resolve(E exception) {
        return ResultBean.build(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

}
