package com.y1ph.easy.design.website.constant;

/**
 * Http状态码
 *
 * @author WFT
 * @since 2022/1/1
 */
public interface HttpStatus {

    /**
     * 请求成功
     */
    int OK = org.springframework.http.HttpStatus.OK.value();

    /**
     * 临时重定向
     */
    int TEMPORARY_REDIRECT = org.springframework.http.HttpStatus.TEMPORARY_REDIRECT.value();

    /**
     * 请求未经授权（需要用户登陆）
     */
    int UNAUTHORIZED = org.springframework.http.HttpStatus.UNAUTHORIZED.value();

    /**
     * 客户端请求有语法错误
     */
    int BAD_REQUEST = org.springframework.http.HttpStatus.BAD_REQUEST.value();

    /**
     * 服务器已经理解请求，但是拒绝执行它（权限不足）
     */
    int FORBIDDEN = org.springframework.http.HttpStatus.FORBIDDEN.value();

    /**
     * 客户端授权证书已失效（需要重新登陆）
     */
    int PROXY_AUTHENTICATION_REQUIRED = org.springframework.http.HttpStatus.PROXY_AUTHENTICATION_REQUIRED.value();

    /**
     * 请求失败,请求所希望得到的资源未被在服务器上发现
     */
    int NOT_FOUND = org.springframework.http.HttpStatus.NOT_FOUND.value();

    /**
     * 请求超时
     */
    int REQUEST_TIMEOUT = org.springframework.http.HttpStatus.REQUEST_TIMEOUT.value();

    /**
     * 服务器遇到了一个未曾预料的状况,导致了它无法完成对请求的处理
     */
    int INTERNAL_SERVER_ERROR = org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value();

}
