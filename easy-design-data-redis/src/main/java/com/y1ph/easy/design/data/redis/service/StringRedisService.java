package com.y1ph.easy.design.data.redis.service;

import java.util.concurrent.TimeUnit;

/**
 * Redis字符串-数据类型接口
 *
 * @author WFT
 * @since 2022/1/3
 */
public interface StringRedisService extends BaseRedisService {

    /**
     * 获取缓存内容
     *
     * @param key {@link String } 缓存名称
     * @param <T> {@link Object}
     * @return {@link Object}
     */
    <T> T get(String key);

    /**
     * 设置缓存
     *
     * @param key   {@link String} 缓存名称
     * @param value {@link Object} 缓存内容
     */
    void set(String key, Object value);

    /**
     * 设置缓存,并设置有效期
     *
     * @param key     {@link String} 缓存名称
     * @param value   {@link Object} 缓存内容
     * @param timeout {@link Long} 缓存有效期（单位：秒）
     */
    default void set(String key, Object value, long timeout) {
        this.set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存,并设置有效期
     *
     * @param key     {@link String} 缓存名称
     * @param value   {@link Object} 缓存内容
     * @param timeout {@link Long} 缓存有效期
     * @param unit    {@link TimeUnit} 时间单位
     */
    void set(String key, Object value, long timeout, TimeUnit unit);

}
