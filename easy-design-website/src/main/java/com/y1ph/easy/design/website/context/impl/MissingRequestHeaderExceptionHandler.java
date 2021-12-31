package com.y1ph.easy.design.website.context.impl;

import com.y1ph.easy.design.website.beans.ResultBean;
import com.y1ph.easy.design.website.constant.HttpStatus;
import com.y1ph.easy.design.website.context.ExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingRequestHeaderException;

/**
 * 缺少请求头异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class MissingRequestHeaderExceptionHandler implements ExceptionHandler<Class<MissingRequestHeaderException>> {

    @Override
    public <E extends Throwable> ResultBean<?> resolve(E exception) {
        return ResultBean.build(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @Override
    public Class<MissingRequestHeaderException> getId() {
        return MissingRequestHeaderException.class;
    }
}
