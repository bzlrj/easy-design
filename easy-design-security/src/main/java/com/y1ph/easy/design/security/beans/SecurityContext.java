package com.y1ph.easy.design.security.beans;

import com.y1ph.easy.design.security.exception.BaseSecurityException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 权限上下文对象
 *
 * @author WFT
 * @since 2022/1/4
 */
@Getter
@Setter
public class SecurityContext implements Serializable {

    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 令牌生成规则
     */
    private String tokenRule;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 用户主体
     */
    private Principal<?> principal;

    /**
     * 权限异常
     */
    private BaseSecurityException exception;

}
