package com.y1ph.easy.design.common;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Java Bean 工厂抽象类
 *
 * @author WFT
 * @since 2021/12/30
 */
public abstract class BaseBeanFactory<Id extends Serializable, Bean extends BaseBean<Id>> implements ApplicationContextAware {

    private Map<Id, Bean> map;

    /**
     * 构建一个非法参数异常
     * 当无法通过调用者传入的参数从map中获取到实例时,将抛出此异常
     *
     * @return {@link IllegalArgumentException}
     */
    protected abstract IllegalArgumentException illegalArgumentException();

    /**
     * 获取Java Bean
     *
     * @param id 主键
     * @return {@link Serializable}
     */
    public final Bean get(Id id) {
        return Optional.ofNullable(this.map.get(id)).orElseThrow(this::illegalArgumentException);
    }

    @Override
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public final void setApplicationContext(ApplicationContext context) throws BeansException {
        //  获取泛型超类
        Type superclass = this.getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            //  获取泛型的类型列表
            Type[] arguments = ((ParameterizedType) superclass).getActualTypeArguments();
            Assert.isTrue(arguments.length > 0, "未定义泛型");
            //  获取Bean的完整名称
            String className = arguments[arguments.length - 1].toString()
                //  去除不必要的字符
                .replaceAll("^class", "")
                .replaceAll("<.*>$", "")
                .trim();
            //  从Spring容器中获取该类型的所有实例
            this.map = context.getBeansOfType((Class<Bean>) Class.forName(className))
                .values()
                .stream()
                .collect(Collectors.toMap(Bean::getId, bean -> bean));
        }
    }

}
