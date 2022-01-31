package com.y1ph.easy.design.security.oauth.service;

import com.y1ph.easy.design.security.oauth.beans.OauthClient;

import java.io.Serializable;

/**
 * 客户端接口
 *
 * @author WFT
 * @since 2022/1/5
 */
public interface ClientService<T extends OauthClient<? extends Serializable>> {

    /**
     * 根据编号获取客户端信息
     *
     * @param id {@link Serializable}
     * @return {@link OauthClient}
     */
    T select(Serializable id);

}
