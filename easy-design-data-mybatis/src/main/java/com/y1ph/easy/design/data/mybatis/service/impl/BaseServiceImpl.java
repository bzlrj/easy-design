package com.y1ph.easy.design.data.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.y1ph.easy.design.common.BaseEntity;
import com.y1ph.easy.design.data.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

/**
 * 公共服务层接口实现类
 *
 * @author WFT
 * @since 2022/1/4
 */
public abstract class BaseServiceImpl<Entity extends BaseEntity<? extends Serializable>, Mapper extends BaseMapper<Entity>>
    implements BaseService<Entity> {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private Mapper mapper;

    private LambdaQueryWrapper<Entity> queryWrapper(Consumer<LambdaQueryWrapper<Entity>> action) {
        LambdaQueryWrapper<Entity> wrapper = Wrappers.lambdaQuery();
        action.accept(wrapper);
        return wrapper;
    }

    private LambdaUpdateWrapper<Entity> updateWrapper(Consumer<LambdaUpdateWrapper<Entity>> action) {
        LambdaUpdateWrapper<Entity> wrapper = Wrappers.lambdaUpdate();
        action.accept(wrapper);
        return wrapper;
    }

    @Override
    public Entity select(Serializable id) {
        return this.mapper.selectById(id);
    }

    @Override
    public Entity select(Consumer<LambdaQueryWrapper<Entity>> consumer) {
        return this.mapper.selectOne(this.queryWrapper(consumer));
    }

    @Override
    public List<Entity> query(Consumer<LambdaQueryWrapper<Entity>> consumer) {
        return this.mapper.selectList(this.queryWrapper(consumer));
    }

    @Override
    public int count(Consumer<LambdaQueryWrapper<Entity>> consumer) {
        return this.mapper.selectCount(this.queryWrapper(consumer));
    }

    @Override
    public IPage<Entity> page(IPage<Entity> page, Consumer<LambdaQueryWrapper<Entity>> consumer) {
        return this.mapper.selectPage(page, this.queryWrapper(consumer));
    }

    @Override
    public void create(Entity param) {
        this.mapper.insert(param);
    }

    @Override
    public void update(Entity param) {
        this.mapper.updateById(param);
    }

    @Override
    public void update(Entity param, Consumer<LambdaUpdateWrapper<Entity>> consumer) {
        this.mapper.update(param, this.updateWrapper(consumer));
    }

    @Override
    public void delete(Serializable id) {
        this.mapper.deleteById(id);
    }

    @Override
    public void delete(Consumer<LambdaQueryWrapper<Entity>> consumer) {
        this.mapper.delete(this.queryWrapper(consumer));
    }
}
