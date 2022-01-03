package com.y1ph.easy.design.data.redis.service.impl;

import com.y1ph.easy.design.data.redis.service.HashRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 哈希-数据类型接口实现
 *
 * @author WFT
 * @since 2022/1/4
 */
@Service
@RequiredArgsConstructor
public class HashRedisServiceImpl extends BaseRedisServiceImpl implements HashRedisService {

    @Override
    public void delete(String key, String hashKey) {
        this.template.opsForHash().delete(key, hashKey);
    }

    @Override
    public <T> T get(String key, String hashKey) {
        HashOperations<String, String, T> operations = this.template.opsForHash();
        return operations.get(key, hashKey);
    }

    @Override
    public <T> Map<String, T> getAll(String key) {
        HashOperations<String, String, T> operations = this.template.opsForHash();
        return operations.entries(key);
    }

    @Override
    public <T> void put(String key, String hashKey, T value) {
        this.template.opsForHash().put(key, hashKey, value);
    }

    @Override
    public <T> void putAll(String key, Map<String, T> map) {
        this.template.opsForHash().putAll(key, map);
    }

    @Override
    public Set<String> getKeys(String key) {
        HashOperations<String, String, Object> operations = this.template.opsForHash();
        return operations.keys(key);
    }

    @Override
    public <T> List<T> values(String key) {
        HashOperations<String, String, T> operations = this.template.opsForHash();
        return operations.values(key);
    }
}
