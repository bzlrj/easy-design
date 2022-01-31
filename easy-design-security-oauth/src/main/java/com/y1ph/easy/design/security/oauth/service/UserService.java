package com.y1ph.easy.design.security.oauth.service;

import com.y1ph.easy.design.common.BaseBean;
import com.y1ph.easy.design.security.beans.Principal;
import com.y1ph.easy.design.security.oauth.beans.GrantParameter;

/**
 * 用户接口
 *
 * @author WFT
 * @since 2022/1/5
 */
public interface UserService<T extends Principal<?>> extends BaseBean<String> {

    /**
     * 用户认证接口,账户密码校验
     *
     * @param parameter   认证参数
     * @param <Parameter> {@link GrantParameter}
     * @return {@link Principal}
     * @throws IllegalArgumentException 当认证失败时将抛出此异常
     */
    <Parameter extends GrantParameter> T grant(Parameter parameter) throws IllegalArgumentException;

}
