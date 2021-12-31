package com.y1ph.easy.design.website.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 忽略响应体注解
 *
 * @author WFT
 * @since 2022/1/1
 */
@Inherited
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface IgnoreResponseBody {
}
