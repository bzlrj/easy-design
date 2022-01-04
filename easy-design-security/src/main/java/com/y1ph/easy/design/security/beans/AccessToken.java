package com.y1ph.easy.design.security.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 访问令牌
 *
 * @author WFT
 * @since 2022/1/4
 */
@Getter
@AllArgsConstructor
public class AccessToken {

    /**
     * 访问令牌
     */
    private final String token;

    /**
     * 令牌生成规则
     */
    private final String rule;

    /**
     * 用户主体
     */
    private final Principal<?> principal;

}
