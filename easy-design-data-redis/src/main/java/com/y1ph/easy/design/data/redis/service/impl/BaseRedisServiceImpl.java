package com.y1ph.easy.design.data.redis.service.impl;

import com.y1ph.easy.design.data.redis.service.BaseRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis接口-基础实现
 *
 * @author WFT
 * @since 2022/1/3
 */
@RequiredArgsConstructor
public abstract class BaseRedisServiceImpl implements BaseRedisService {

    @Autowired
    protected RedisTemplate<String,Object> template;

    /**
     * 删除缓存
     *
     * @param key {@link String}
     */
    @Override
    public void delete(String key) {
        this.template.delete(key);
    }

    /**
     * 删除缓存
     *
     * @param keys {@link Collection}
     */
    @Override
    public void delete(Collection<String> keys) {
        this.template.delete(keys);
    }

    /**
     * 查询符合条件的所有密钥
     *
     * @param pattern {@link String}
     * @return {@link Set}
     */
    @Override
    public Set<String> keys(String pattern) {
        return this.template.keys(pattern);
    }

    /**
     * 获取缓存的有效期
     *
     * @param key {@link String} 缓存名称
     * @return {@link Long} 有效期（单位：秒）
     */
    @Override
    public Long expire(String key) {
        return this.expire(key, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存的有效期
     *
     * @param key  {@link String} 缓存名称
     * @param unit {@link TimeUnit} 时间单位
     * @return {@link Long}
     */
    @Override
    public Long expire(String key, TimeUnit unit) {
        return this.template.getExpire(key, unit);
    }

    /**
     * 设置缓存的有效期
     *
     * @param key     {@link String} 缓存名称
     * @param timeout {@link Long} 有效期（单位：秒）
     */
    @Override
    public void expire(String key, long timeout) {
        this.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存的有效期
     *
     * @param key     {@link String} 缓存名称
     * @param timeout {@link Long} 有效期
     * @param unit    {@link TimeUnit} 时间单位
     */
    @Override
    public void expire(String key, long timeout, TimeUnit unit) {
        this.template.expire(key, timeout, unit);
    }

}
