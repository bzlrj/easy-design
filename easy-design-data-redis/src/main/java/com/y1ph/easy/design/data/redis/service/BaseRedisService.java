package com.y1ph.easy.design.data.redis.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 公共的Redis接口
 *
 * @author WFT
 * @since 2022/1/3
 */
public abstract class BaseRedisService {

    /**
     * (non-Javadoc)
     * @return {@link RedisTemplate}
     */
    protected abstract RedisTemplate<String, Object> getTemplate();

    /**
     * 删除缓存
     *
     * @param key {@link String}
     */
    public void delete(String key) {
        this.getTemplate().delete(key);
    }

    /**
     * 删除缓存
     *
     * @param keys {@link Collection}
     */
    public void delete(Collection<String> keys) {
        this.getTemplate().delete(keys);
    }

    /**
     * 查询符合条件的所有密钥
     *
     * @param pattern {@link String}
     * @return {@link Set}
     */
    public Set<String> keys(String pattern) {
        return this.getTemplate().keys(pattern);
    }

    /**
     * 获取缓存的有效期
     *
     * @param key {@link String} 缓存名称
     * @return {@link Long} 有效期（单位：秒）
     */
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
    @SuppressWarnings("WeakerAccess")
    public Long expire(String key, TimeUnit unit) {
        return this.getTemplate().getExpire(key, unit);
    }

    /**
     * 设置缓存的有效期
     *
     * @param key     {@link String} 缓存名称
     * @param timeout {@link Long} 有效期（单位：秒）
     */
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
    @SuppressWarnings("WeakerAccess")
    public void expire(String key, long timeout, TimeUnit unit) {
        this.getTemplate().expire(key, timeout, unit);
    }

}
