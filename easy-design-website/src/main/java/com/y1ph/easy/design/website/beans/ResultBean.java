package com.y1ph.easy.design.website.beans;

import lombok.Getter;

import java.io.Serializable;

/**
 * 返回值类型
 *
 * @author WFT
 * @since 2022/1/1
 */
@Getter
public class ResultBean<Data> implements Serializable {

    private int code;

    private String msg;

    private Data data;

    private ResultBean(int code, String msg, Data data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultBean<T> build(int code, String msg) {
        return build(code, msg, null);
    }

    public static <T> ResultBean<T> build(int code, String msg, T data) {
        return new ResultBean<>(code, msg, data);
    }

}
