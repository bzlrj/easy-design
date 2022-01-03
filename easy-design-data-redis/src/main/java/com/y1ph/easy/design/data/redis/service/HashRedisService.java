package com.y1ph.easy.design.data.redis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 哈希-数据类型接口
 *
 * @author WFT
 * @since 2022/1/4
 */
public interface HashRedisService extends BaseRedisService {

    /**
     * 删除缓存
     *
     * @param key     {@link String}
     * @param hashKey {@link String}
     */
    void delete(String key, String hashKey);

    /**
     * 查询单个
     *
     * @param key     {@link String}
     * @param hashKey {@link String}
     * @param <T>     {@link Object}
     * @return 缓存内容
     */
    <T> T get(String key, String hashKey);

    /**
     * 查询全部
     *
     * @param key {@link String}
     * @param <T> {@link Object}
     * @return {@link Map}
     */
    <T> Map<String, T> getAll(String key);

    /**
     * 添加单个
     *
     * @param key     {@link String}
     * @param hashKey {@link String}
     * @param value   {@link Object}
     * @param <T>     {@link Object}
     */
    <T> void put(String key, String hashKey, T value);

    /**
     * 批量添加
     *
     * @param key {@link String}
     * @param map {@link Map}
     * @param <T> {@link Object}
     */
    <T> void putAll(String key, Map<String, T> map);

    /**
     * 获取所有的键
     *
     * @param key {@link String}
     * @return {@link Set}
     */
    Set<String> getKeys(String key);

    /**
     * 获取所有的值
     *
     * @param key {@link String}
     * @param <T> {@link Object}
     * @return {@link List}
     */
    <T> List<T> values(String key);


}
