package com.y1ph.easy.design.security.oauth.beans;

import com.y1ph.easy.design.security.oauth.enums.GrantType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 认证参数
 *
 * @author WFT
 * @since 2022/1/5
 */
@Getter
@Setter
public class GrantParameter implements Serializable {

    /**
     * 用户名,必填
     */
    private String username;

    /**
     * 密码,必填
     */
    private String password;

    /**
     * 加密盐,必填
     */
    private String salt;

    /**
     * 认证模式,必填
     */
    private GrantType grantType;

}
