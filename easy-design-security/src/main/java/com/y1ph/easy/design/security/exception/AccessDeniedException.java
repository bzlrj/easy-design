package com.y1ph.easy.design.security.exception;

import com.y1ph.easy.design.website.constant.HttpStatus;

/**
 * 403 异常
 *
 * @author WFT
 * @since 2022/1/4
 */
public class AccessDeniedException extends BaseSecurityException {

    public AccessDeniedException() {
        this("You don't have permission to access on this server.");
    }

    @SuppressWarnings("WeakerAccess")
    public AccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }

}
