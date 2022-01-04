package com.y1ph.easy.design.security.service;

import com.y1ph.easy.design.security.exception.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 令牌存储接口
 *
 * @author WFT
 * @since 2022/1/4
 */
public interface TokenStorageService {

    String BEARER = "Bearer";

    /**
     * 获取访问令牌,默认从请求头中获取
     *
     * @param request {@link HttpServletRequest}
     * @return {@link String}
     * @throws UnauthorizedException 未登录的情况下,将抛出401异常
     */
    default String getAccessToken(HttpServletRequest request) throws UnauthorizedException {
        //  从请求头中获取令牌
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //  若无法从请求头中获取到访问令牌,则标识当前客户端尚未进行认证,抛出401异常
        if (StringUtils.isEmpty(token)) {
            throw new UnauthorizedException();
        }
        //  截取访问令牌
        return token.replace(BEARER, "").trim();
    }

}
