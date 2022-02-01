package com.y1ph.easy.design.data.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.y1ph.easy.design.common.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * 公共服务层接口
 *
 * @author WFT
 * @since 2022/1/4
 */
public interface BaseService<Entity extends BaseEntity> {

    /**
     * 根据编号查询
     *
     * @param id {@link Serializable} 编号
     * @return {@link BaseEntity} 查询结果
     */
    Entity select(Serializable id);

    /**
     * 根据查询条件查询
     *
     * @param consumer {@link Consumer} 查询条件
     * @return {@link BaseEntity} 如果结果不唯一,将抛出异常
     */
    Entity select(Consumer<LambdaQueryWrapper<Entity>> consumer);

    /**
     * 查询列表
     *
     * @return {@link List} 查询结果
     */
    default List<Entity> query() {
        return this.query(wrapper -> {

        });
    }

    /**
     * 根据编号列表查询
     *
     * @param list {@link Collection} 编号列表不能为空
     * @return {@link List} 查询结果
     */
    default List<Entity> query(Collection<? extends Serializable> list) {
        return this.query(wrapper -> wrapper.in(Entity::getId, list));
    }

    /**
     * 根据条件查询列表
     *
     * @param consumer {@link Consumer} 查询条件
     * @return {@link List} 查询结果
     */
    List<Entity> query(Consumer<LambdaQueryWrapper<Entity>> consumer);

    /**
     * 查询总记录数
     *
     * @return {@link Integer}
     */
    default int count() {
        return this.count(wrapper -> {

        });
    }

    /**
     * 根据条件查询总记录数
     *
     * @param consumer {@link Consumer} 查询条件
     * @return {@link Integer} 总记录数
     */
    int count(Consumer<LambdaQueryWrapper<Entity>> consumer);

    /**
     * 分页查询
     *
     * @param page {@link IPage} 分页条件
     * @return {@link IPage} 查询结果
     */
    default IPage<Entity> page(IPage<Entity> page) {
        return this.page(page, wrapper -> {

        });
    }

    /**
     * 根据条件分页查询
     *
     * @param page     {@link IPage} 分页条件
     * @param consumer {@link Consumer} 查询条件
     * @return {@link IPage} 查询结果
     */
    IPage<Entity> page(IPage<Entity> page, Consumer<LambdaQueryWrapper<Entity>> consumer);

    /**
     * 新增一条数据
     *
     * @param param {@link BaseEntity} 数据
     */
    void create(Entity param);

    /**
     * 新增多条数据
     *
     * @param list {@link List} 数据列表
     */
    default void create(List<Entity> list) {
        list.forEach(this::create);
    }

    /**
     * 修改单条数据
     *
     * @param param {@link BaseEntity} 需要更新的数据
     */
    void update(Entity param);

    /**
     * 修改多条数据
     *
     * @param param {@link BaseEntity} 需要更新的数据
     * @param list  {@link Collection} 编号列表
     */
    default void update(Entity param, Collection<? extends Serializable> list) {
        this.update(param, wrapper -> wrapper.in(Entity::getId, list));
    }

    /**
     * 根据条件修改数据
     *
     * @param consumer {@link Consumer} 修改条件
     */
    default void update(Consumer<LambdaUpdateWrapper<Entity>> consumer) {
        this.update(null, consumer);
    }

    /**
     * 根据条件修改数据
     *
     * @param param    {@link BaseEntity} 需要更新的数据
     * @param consumer {@link Consumer} 修改条件
     */
    void update(Entity param, Consumer<LambdaUpdateWrapper<Entity>> consumer);

    /**
     * 删除单条数据
     *
     * @param id {@link Serializable} 编号
     */
    void delete(Serializable id);

    /**
     * 删除多条数据
     *
     * @param list {@link Collection} 编号列表
     */
    default void delete(Collection<? extends Serializable> list) {
        this.delete(wrapper -> wrapper.in(Entity::getId, list));
    }

    /**
     * 根据条件删除数据
     *
     * @param consumer {@link Consumer} 删除条件
     */
    void delete(Consumer<LambdaQueryWrapper<Entity>> consumer);

}
