package com.y1ph.easy.design.security.oauth.beans;

import com.y1ph.easy.design.common.BaseEntity;
import com.y1ph.easy.design.common.enums.Locked;
import com.y1ph.easy.design.security.oauth.enums.GrantType;

import java.io.Serializable;

/**
 * Oauth 客户端接口
 *
 * @author WFT
 * @since 2022/1/5
 */
public interface OauthClient<Id extends Serializable> extends BaseEntity<Id> {

    /**
     * 获取客户端密钥
     *
     * @return {@link String}
     */
    String getSecret();

    /**
     * 获取代理客户端编号
     *
     * @return {@link Serializable}
     */
    Id getProxyClientId();

    /**
     * 获取支持的授权方式
     *
     * @return {@link GrantType}
     */
    GrantType getGrantType();

    /**
     * 获取锁定标记
     *
     * @return {@link Locked}
     */
    Locked getLocked();

}
