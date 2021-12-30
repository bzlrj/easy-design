package com.y1ph.easy.design.common.utils;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * @author WFT
 * @since 2021/12/30
 */
@FunctionalInterface
public interface Function<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     * @throws Exception 异常
     */
    R apply(T t) throws Exception;

}
