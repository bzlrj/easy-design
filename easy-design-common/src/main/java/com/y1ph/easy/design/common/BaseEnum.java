package com.y1ph.easy.design.common;

import java.io.Serializable;

/**
 * 公共枚举接口
 *
 * @author WFT
 * @since 2022/1/1
 */
public interface BaseEnum<T extends Serializable> {

    /**
     * 获取值
     *
     * @return {@link Serializable}
     */
    T getValue();

}
