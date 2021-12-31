package com.y1ph.easy.design.website.utils;

import org.springframework.web.client.RestTemplate;

/**
 * Http工具类
 *
 * @author WFT
 * @since 2021/12/31
 */
public class HttpUtil {

    /**
     * 私有化构造函数
     */
    private HttpUtil() {

    }

    /**
     * 以静态内部类实例化当前对象,从而达到懒汉式单例的效果
     */
    private static class Holder {

        private final static RestTemplate TEMPLATE = new RestTemplate();

    }

    /**
     * @return {@link RestTemplate}
     */
    public static RestTemplate getTemplate() {
        return Holder.TEMPLATE;
    }

}
