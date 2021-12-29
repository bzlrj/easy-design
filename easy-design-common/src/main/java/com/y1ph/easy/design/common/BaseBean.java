package com.y1ph.easy.design.common;

import java.io.Serializable;

/**
 * Java Bean 公共接口
 *
 * @author WFT
 * @since 2021/12/30
 */
public interface BaseBean<Id extends Serializable> {

    /**
     * 获取主键
     *
     * @return {@link Serializable}
     */
    Id getId();

}
