package com.y1ph.easy.design.website.context.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 方法参数类型不匹配异常处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class MethodArgumentTypeMismatchExceptionHandler extends BaseParameterExceptionHandler<Class<MethodArgumentTypeMismatchException>> {

    @Override
    public Class<MethodArgumentTypeMismatchException> getId() {
        return MethodArgumentTypeMismatchException.class;
    }
}
