package com.y1ph.easy.design.security.beans;

import com.y1ph.easy.design.common.BaseEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * 用户主体接口
 *
 * @author WFT
 * @since 2022/1/4
 */
public interface Principal<Id extends Serializable> extends BaseEntity<Id> {

    /**
     * 获取用户名
     *
     * @return {@link String}
     */
    String getUsername();

    /**
     * 获取权限列表
     *
     * @return {@link Collection}
     */
    Collection<String> getAuthorities();

    /**
     * 设置权限列表
     *
     * @param authorities {@link Collection}
     */
    void setAuthorities(Collection<String> authorities);

}
