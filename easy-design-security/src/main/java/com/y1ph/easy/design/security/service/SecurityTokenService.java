package com.y1ph.easy.design.security.service;

import com.y1ph.easy.design.common.BaseBean;
import com.y1ph.easy.design.security.beans.AccessToken;
import com.y1ph.easy.design.security.beans.Principal;
import com.y1ph.easy.design.security.beans.SecurityContext;
import com.y1ph.easy.design.security.exception.AuthenticationException;

/**
 * 访问令牌接口
 *
 * @author WFT
 * @since 2022/1/4
 */
public interface SecurityTokenService extends BaseBean<String> {

    /**
     * 获取当前登录用户的主体信息
     *
     * @param context {@link SecurityContext} 权限上下文
     * @return {@link Principal} 用户主体信息
     * @throws AuthenticationException 登录超时的情况下,将抛出407异常
     */
    Principal<?> getPrincipal(SecurityContext context) throws AuthenticationException;

    /**
     * 生成访问令牌
     *
     * @param principal {@link Principal} 用户主体信息
     * @param clientId  {@link String} 客户端编号
     * @param <T>       {@link Principal}
     * @return {@link AccessToken}
     */
    <T extends Principal<?>> AccessToken generate(T principal, String clientId);

    /**
     * 移除访问令牌
     *
     * @param context {@link SecurityContext} 权限上下文
     */
    default void remove(SecurityContext context) {

    }

}
