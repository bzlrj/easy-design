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
     * 用户主体
     */
    private final Principal<?> principal;

}
