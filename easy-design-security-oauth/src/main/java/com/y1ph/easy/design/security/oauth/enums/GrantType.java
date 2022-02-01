package com.y1ph.easy.design.security.oauth.enums;

import com.y1ph.easy.design.common.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 认证模式
 *
 * @author WFT
 * @since 2022/1/5
 */
@Getter
@RequiredArgsConstructor
public enum GrantType implements BaseEnum<Integer> {

    /**
     * 密码模式
     */
    password(1),

    /**
     * 授权码模式
     */
    authorization_code(2),
    ;

    private final Integer value;

}
