package com.y1ph.easy.design.data.redis.service.impl;

import com.y1ph.easy.design.data.redis.service.StringRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis字符串-数据类型接口实现
 *
 * @author WFT
 * @since 2022/1/4
 */
@Service
@RequiredArgsConstructor
public class StringRedisServiceImpl extends BaseRedisServiceImpl implements StringRedisService {

    private ValueOperations<String, Object> getOperations() {
        return this.template.opsForValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) getOperations().get(key);
    }

    @Override
    public void set(String key, Object value) {
        this.getOperations().set(key, value);
    }

    @Override
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        this.getOperations().set(key, value, timeout, unit);
    }

}
