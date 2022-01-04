package com.y1ph.easy.design.security.annotation;

import java.lang.annotation.*;

/**
 * 授权注解
 *
 * @author WFT
 * @since 2022/1/4
 */
@Inherited
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PreAuthorize {

    /**
     * @return 权限标识列表
     */
    String[] value() default {};

}
