package com.y1ph.easy.design.security.service.impl;

import com.y1ph.easy.design.data.redis.service.StringRedisService;
import com.y1ph.easy.design.security.beans.AccessToken;
import com.y1ph.easy.design.security.beans.Principal;
import com.y1ph.easy.design.security.beans.SecurityContext;
import com.y1ph.easy.design.security.exception.AuthenticationException;
import com.y1ph.easy.design.security.service.SecurityTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Redis令牌接口实现类
 *
 * @author WFT
 * @since 2022/1/4
 */
@Service
@RequiredArgsConstructor
public class RedisSecurityTokenServiceImpl implements SecurityTokenService {

    private final StringRedisService redisService;

    private final static int VALIDITY = 60 * 60 * 24;

    /**
     * 获取缓存名称
     *
     * @param context {@link SecurityContext} 权限上下文
     * @return {@link String}
     */
    private String getCacheName(SecurityContext context) {
        return this.getCacheName(context.getClientId(), context.getAccessToken());
    }

    /**
     * 获取缓存名称
     *
     * @param clientId {@link String} 客户端编号
     * @param token    {@link String} 访问令牌
     * @return {@link String}
     */
    private String getCacheName(String clientId, String token) {
        return String.format("online_user:%s:%s", clientId, token);
    }

    @Override
    public Principal<?> getPrincipal(SecurityContext context) throws AuthenticationException {
        //  获取缓存名称
        String cacheName = this.getCacheName(context);
        //  从缓存中获取用户主体信息
        Principal<?> principal = this.redisService.get(cacheName);
        //  当无法从缓存中获取到用户主体,表示当前的访问令牌已超时,抛出407异常
        if (null == principal) {
            throw new AuthenticationException();
        }
        //  为访问令牌续期
        this.redisService.expire(cacheName, VALIDITY);
        return principal;
    }

    @Override
    public <T extends Principal<?>> AccessToken generate(T principal, String clientId) {
        //  生成访问令牌
        String token = UUID.randomUUID().toString();
        //  将主体信息保存到Redis中
        this.redisService.set(this.getCacheName(clientId, token), principal, VALIDITY);
        return new AccessToken(token, this.getId(), principal);
    }

    @Override
    public void remove(SecurityContext context) {
        this.redisService.delete(this.getCacheName(context));
    }

    @Override
    public String getId() {
        return "redis";
    }

}
