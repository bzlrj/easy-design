package com.y1ph.easy.design.security.oauth.service.impl;

import com.y1ph.easy.design.common.enums.Locked;
import com.y1ph.easy.design.security.beans.Principal;
import com.y1ph.easy.design.security.oauth.beans.OauthClient;
import com.y1ph.easy.design.security.oauth.beans.OauthParameter;
import com.y1ph.easy.design.security.oauth.context.UserFactory;
import com.y1ph.easy.design.security.oauth.enums.GrantType;
import com.y1ph.easy.design.security.oauth.service.ClientService;
import com.y1ph.easy.design.security.oauth.service.GrantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 密码模式认证接口-实现类
 *
 * @author WFT
 * @since 2022/1/5
 */
@Service
@RequiredArgsConstructor
public class CipherGrantServiceImpl implements GrantService {

    private final ClientService<? extends OauthClient<?>> clientService;
    private final UserFactory factory;

    @Override
    public Principal<?> grant(OauthParameter parameter) throws IllegalArgumentException {
        //  参数校验
        Assert.hasText(parameter.getUsername(), "用户名不能为空");
        Assert.hasText(parameter.getPassword(), "密码不能为空");
        //  获取客户端
        OauthClient<? extends Serializable> client = this.clientService.select(parameter.getClientId());
        Assert.isTrue(this.getId().equals(client.getGrantType()), "当前客户端不支持密码模式");
        Assert.isTrue(Locked.enable.equals(client.getLocked()), "当前客户端为锁定状态");
        Assert.isTrue(client.getSecret().equals(parameter.getClientSecret()), "客户端密钥不正确");
        //  用户认证
        return this.factory.get(client.getProxyClientId().toString()).grant(parameter);
    }

    @Override
    public GrantType getId() {
        return GrantType.password;
    }

}
