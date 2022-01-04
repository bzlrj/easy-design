package com.y1ph.easy.design.security.exception;

import com.y1ph.easy.design.website.constant.HttpStatus;

/**
 * 401 异常
 *
 * @author WFT
 * @since 2022/1/4
 */
public class UnauthorizedException extends BaseSecurityException {

    public UnauthorizedException() {
        this("Full authentication is required to access this resource.");
    }

    @SuppressWarnings("WeakerAccess")
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

}
