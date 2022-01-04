package com.y1ph.easy.design.data.mybatis.handler;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.y1ph.easy.design.common.BaseEntity;
import com.y1ph.easy.design.common.utils.JsonUtil;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Json处理器
 *
 * @author WFT
 * @since 2022/1/4
 */
@RequiredArgsConstructor
public class JsonTypeHandler<T extends BaseEntity<? extends Serializable>> extends AbstractJsonTypeHandler<T> {

    private final Class<T> clazz;

    @Override
    protected T parse(String json) {
        return JsonUtil.getInstance().toBean(json, this.clazz);
    }

    @Override
    protected String toJson(T obj) {
        return JsonUtil.getInstance().toJson(obj);
    }
}
