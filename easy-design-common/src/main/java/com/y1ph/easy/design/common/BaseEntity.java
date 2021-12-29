package com.y1ph.easy.design.common;

import java.io.Serializable;

/**
 * 公共实体类接口
 *
 * @author WFT
 * @since 2021/12/30
 */
public interface BaseEntity<Id extends Serializable> extends BaseBean<Id> {

    /**
     * 设置主键
     *
     * @param id {@link Serializable}
     */
    void setId(Id id);

}
