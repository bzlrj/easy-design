package com.y1ph.easy.design.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.List;

/**
 * Json 工具类
 *
 * @author WFT
 * @since 2021/12/30
 */
@Getter
public class JsonUtil {

    private final ObjectMapper mapper;

    /**
     * 私有化构造函数
     */
    private JsonUtil(ObjectMapper mapper) {
        //  忽略不存在的字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //  忽略value为null时，key的输出
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //  禁用对日期以时间戳方式输出的特性
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
        //  序列换成json时,将所有的long变成string
        SimpleModule module = new SimpleModule();
        module.addSerializer(Long.class, ToStringSerializer.instance);
        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
        mapper.registerModule(module);
        this.mapper = mapper;
    }

    /**
     * 以静态内部类实例化当前对象,从而达到懒汉式单例的效果
     */
    private static class Holder {

        private final static JsonUtil INSTANCE = new JsonUtil(new ObjectMapper());

    }

    /**
     * 获取当前实例对象
     *
     * @return {@link JsonUtil}
     */
    public static JsonUtil getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 将对象转为Json字符串
     *
     * @param obj {@link Object} 对象
     * @return {@link String} Json字符串
     */
    @SneakyThrows
    public String toJson(Object obj) {
        return this.mapper.writeValueAsString(obj);
    }

    /**
     * 将字符串转为对象
     *
     * @param json  {@link String} Json字符串
     * @param clazz {@link Class} 对象类型
     * @return {@link Class}
     */
    @SneakyThrows
    public <T> T toBean(String json, Class<T> clazz) {
        return this.mapper.readValue(json, clazz);
    }

    /**
     * 将字符串转为泛型List集合
     *
     * @param json  {@link String} Json字符串
     * @param clazz {@link Class} 对象类型
     * @return {@link Class}
     */
    @SneakyThrows
    public <T> List<T> toList(String json, Class<T> clazz) {
        return this.mapper.readValue(json, this.mapper.getTypeFactory().constructParametricType(List.class, clazz));
    }

}
