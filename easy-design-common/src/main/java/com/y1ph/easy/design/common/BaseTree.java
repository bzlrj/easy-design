package com.y1ph.easy.design.common;

import java.io.Serializable;
import java.util.List;

/**
 * 树形实体类接口
 *
 * @author WFT
 * @since 2022/1/1
 */
public interface BaseTree<Id extends Serializable, T extends BaseEntity<Id>> extends BaseEntity<Id> {

    /**
     * 获取父级编号
     *
     * @return {@link Serializable}
     */
    Id getPid();

    /**
     * 获取排序值
     *
     * @return {@link Integer}
     */
    Integer getSort();

    /**
     * 获取子节点列表
     *
     * @return {@link List}
     */
    List<T> getChildren();

    /**
     * 设置子节点列表
     *
     * @param list {@link List}
     */
    void setChildren(List<T> list);

}
