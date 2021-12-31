package com.y1ph.easy.design.website.context.impl;

import com.y1ph.easy.design.website.beans.ResultBean;
import com.y1ph.easy.design.website.constant.HttpStatus;
import com.y1ph.easy.design.website.context.ExceptionHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;

/**
 * 请求超时异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class TimeoutExceptionHandler implements ExceptionHandler<Class<TimeoutException>> {

    @Override
    public <E extends Throwable> ResultBean<?> resolve(E exception) {
        return ResultBean.build(HttpStatus.REQUEST_TIMEOUT,exception.getMessage());
    }

    @Override
    public Class<TimeoutException> getId() {
        return TimeoutException.class;
    }
}
