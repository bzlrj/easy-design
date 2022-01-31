package com.y1ph.easy.design.security.oauth.service;

import com.y1ph.easy.design.common.BaseBean;
import com.y1ph.easy.design.security.beans.Principal;
import com.y1ph.easy.design.security.oauth.beans.OauthParameter;
import com.y1ph.easy.design.security.oauth.enums.GrantType;

/**
 * 认证接口
 *
 * @author WFT
 * @since 2022/1/5
 */
public interface GrantService extends BaseBean<GrantType> {

    /**
     * 认证接口
     *
     * @param parameter {@link OauthParameter} 认证参数
     * @return {@link Principal}
     * @throws IllegalArgumentException 当认证失败时将抛出此异常
     */
    Principal<?> grant(OauthParameter parameter) throws IllegalArgumentException;

}
