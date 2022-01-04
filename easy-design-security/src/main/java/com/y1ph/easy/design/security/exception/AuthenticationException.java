package com.y1ph.easy.design.security.exception;

import com.y1ph.easy.design.website.constant.HttpStatus;

/**
 * 407 异常
 *
 * @author WFT
 * @since 2022/1/4
 */
public class AuthenticationException extends BaseSecurityException {


    public AuthenticationException() {
        this("Please replace with a new certificate. Current certificate has expired.");
    }

    @SuppressWarnings("WeakerAccess")
    public AuthenticationException(String message) {
        super(message, HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
    }

}
