package com.y1ph.easy.design.website.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Web 工具类
 *
 * @author WFT
 * @since 2021/12/31
 */
public class WebUtil {

    /**
     * 私有化构造函数
     */
    private WebUtil() {

    }


    /**
     * 以静态内部类实例化当前对象,从而达到懒汉式单例的效果
     */
    private static class Holder {

        private final static WebUtil INSTANCE = new WebUtil();

        /**
         * 请求头：可获取到Ip信息
         */
        private final static String[] IP_HEADER_ARRAY = {
            "X-Requested-For",
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"
        };

    }

    /**
     * 获取当前实例
     *
     * @return {@link WebUtil}
     */
    public static WebUtil getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取HttpServletRequest对象
     *
     * @return {@link HttpServletRequest}
     */
    @SuppressWarnings("WeakerAccess")
    public HttpServletRequest getRequest() {
        RequestAttributes attributes = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
            .orElseThrow(() -> new RuntimeException("request is null"));
        return ((ServletRequestAttributes) attributes).getRequest();
    }

    /**
     * 获取Ip地址
     *
     * @param request {@link HttpServletRequest}
     * @return {@link String}
     */
    @SuppressWarnings("WeakerAccess")
    public String getIp(HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        for (String header : Holder.IP_HEADER_ARRAY) {
            String value = request.getHeader(header);
            if (StringUtils.hasText(value) && !"unknown".equalsIgnoreCase(value)) {
                ip = value;
                break;
            }
        }
        return ip;
    }

    public String getIp(){
        return this.getIp(this.getRequest());
    }

}
