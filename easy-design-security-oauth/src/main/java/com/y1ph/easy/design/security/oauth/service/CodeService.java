package com.y1ph.easy.design.security.oauth.service;

import com.y1ph.easy.design.security.beans.Principal;
import com.y1ph.easy.design.security.oauth.beans.OauthClient;
import com.y1ph.easy.design.security.oauth.beans.OauthParameter;

/**
 * 授权码接口
 *
 * @author WFT
 * @since 2022/1/6
 */
public interface CodeService {

    /**
     * 生成授权码
     *
     * @param principal 用户主体信息
     * @param client    客户端信息
     * @param <P>       {@link Principal}
     * @param <C>       {@link OauthClient}
     * @return {@link String}
     */
    <P extends Principal<?>, C extends OauthClient<?>> String generate(P principal, C client);

    /**
     * 校验授权码
     *
     * @param parameter {@link OauthParameter}
     * @return {@link Principal}
     */
    Principal<?> checker(OauthParameter parameter);
}
