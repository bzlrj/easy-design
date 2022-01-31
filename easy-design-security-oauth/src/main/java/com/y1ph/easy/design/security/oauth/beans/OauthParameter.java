package com.y1ph.easy.design.security.oauth.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * Oauth 认证参数
 *
 * @author WFT
 * @since 2022/1/5
 */
@Getter
@Setter
public class OauthParameter extends GrantParameter {

    /**
     * 客户端编号,必填
     */
    private String clientId;

    /**
     * 客户端密钥,必填
     */
    private String clientSecret;

    /**
     * 授权码,授权码模式下必填
     */
    private String code;

}
