package com.y1ph.easy.design.website.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author WFT
 * @since 2022/1/1
 */
@Getter
public class BizException extends RuntimeException {

    /**
     * Http状态码
     */
    private final int code;

    /**
     * 数据
     */
    private final Object data;

    public BizException(String message, int code) {
        this(message, code, null);
    }

    @SuppressWarnings("WeakerAccess")
    public BizException(String message, int code, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }
}
