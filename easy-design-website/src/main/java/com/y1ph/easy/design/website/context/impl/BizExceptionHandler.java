package com.y1ph.easy.design.website.context.impl;

import com.y1ph.easy.design.website.beans.ResultBean;
import com.y1ph.easy.design.website.context.ExceptionHandler;
import com.y1ph.easy.design.website.exception.BizException;
import org.springframework.stereotype.Component;

/**
 * 业务异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class BizExceptionHandler implements ExceptionHandler<Class<BizException>> {

    @Override
    public <E extends Throwable> ResultBean<?> resolve(E exception) {
        BizException e = (BizException) exception;
        return ResultBean.build(e.getCode(), e.getMessage(), e.getData());
    }

    @Override
    public Class<BizException> getId() {
        return BizException.class;
    }
}
