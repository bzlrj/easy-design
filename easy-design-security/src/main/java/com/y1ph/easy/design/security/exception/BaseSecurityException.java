package com.y1ph.easy.design.security.exception;

import com.y1ph.easy.design.website.exception.BizException;

/**
 * 权限异常父类
 *
 * @author WFT
 * @since 2022/1/4
 */
public class BaseSecurityException extends BizException {

    BaseSecurityException(String message, int code) {
        super(message, code);
    }

}
