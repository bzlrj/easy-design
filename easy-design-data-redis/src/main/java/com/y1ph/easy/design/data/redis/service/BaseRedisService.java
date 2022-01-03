package com.y1ph.easy.design.data.redis.service;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 公共的Redis接口
 *
 * @author WFT
 * @since 2022/1/3
 */
public interface BaseRedisService {

    /**
     * 删除缓存
     *
     * @param key {@link String}
     */
    void delete(String key);

    /**
     * 删除缓存
     *
     * @param keys {@link Collection}
     */
    void delete(Collection<String> keys);

    /**
     * 查询符合条件的所有密钥
     *
     * @param pattern {@link String}
     * @return {@link Set}
     */
    Set<String> keys(String pattern);

    /**
     * 获取缓存的有效期
     *
     * @param key {@link String} 缓存名称
     * @return {@link Long} 有效期（单位：秒）
     */
    Long expire(String key);

    /**
     * 获取缓存的有效期
     *
     * @param key  {@link String} 缓存名称
     * @param unit {@link TimeUnit} 时间单位
     * @return {@link Long}
     */
    Long expire(String key, TimeUnit unit);

    /**
     * 设置缓存的有效期
     *
     * @param key     {@link String} 缓存名称
     * @param timeout {@link Long} 有效期（单位：秒）
     */
    void expire(String key, long timeout);

    /**
     * 设置缓存的有效期
     *
     * @param key     {@link String} 缓存名称
     * @param timeout {@link Long} 有效期
     * @param unit    {@link TimeUnit} 时间单位
     */
    void expire(String key, long timeout, TimeUnit unit);

}
