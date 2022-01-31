package com.y1ph.easy.design.security.oauth.service.impl;

import com.y1ph.easy.design.data.redis.service.StringRedisService;
import com.y1ph.easy.design.security.beans.Principal;
import com.y1ph.easy.design.security.oauth.beans.GrantCode;
import com.y1ph.easy.design.security.oauth.beans.OauthClient;
import com.y1ph.easy.design.security.oauth.beans.OauthParameter;
import com.y1ph.easy.design.security.oauth.enums.GrantType;
import com.y1ph.easy.design.security.oauth.service.CodeService;
import com.y1ph.easy.design.security.oauth.service.GrantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * 授权码模式认证接口-实现类
 *
 * @author WFT
 * @since 2022/1/6
 */
@Service
@RequiredArgsConstructor
public class CodeGrantServiceImpl implements GrantService, CodeService {

    private final StringRedisService redisService;

    @Override
    public Principal<?> grant(OauthParameter parameter) throws IllegalArgumentException {
        //  参数校验
        Assert.hasText(parameter.getCode(), "授权码不能为空");
        //  校验授权码
        return this.checker(parameter);
    }

    private String getCacheName(String code) {
        return String.format("oauth:code:%s", code);
    }

    @Override
    public <P extends Principal<?>, C extends OauthClient<?>> String generate(P principal, C client) {
        String code = UUID.randomUUID().toString();
        //  将授权码信息保存到Redis中,有效期为10分钟
        this.redisService.set(this.getCacheName(code), new GrantCode(principal, client), 600);
        return code;
    }

    @Override
    public Principal<?> checker(OauthParameter parameter) {
        //  获取授权码
        GrantCode code = this.redisService.get(this.getCacheName(parameter.getCode()));
        Assert.notNull(code, "无效的授权码");
        //  客户端密码校验
        String cipherText = DigestUtils.md5DigestAsHex((code.getClient().getSecret() + parameter.getSalt()).getBytes());
        Assert.isTrue(cipherText.equals(parameter.getClientSecret()), "客户端密钥不正确");
        return code.getPrincipal();
    }

    @Override
    public GrantType getId() {
        return GrantType.authorization_code;
    }
}
