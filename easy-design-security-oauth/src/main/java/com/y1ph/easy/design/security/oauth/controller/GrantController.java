package com.y1ph.easy.design.security.oauth.controller;

import com.y1ph.easy.design.common.enums.Locked;
import com.y1ph.easy.design.security.annotation.PreAuthorize;
import com.y1ph.easy.design.security.beans.AccessToken;
import com.y1ph.easy.design.security.beans.Principal;
import com.y1ph.easy.design.security.oauth.beans.OauthClient;
import com.y1ph.easy.design.security.oauth.beans.OauthParameter;
import com.y1ph.easy.design.security.oauth.context.GrantFactory;
import com.y1ph.easy.design.security.oauth.enums.GrantType;
import com.y1ph.easy.design.security.oauth.service.ClientService;
import com.y1ph.easy.design.security.oauth.service.CodeService;
import com.y1ph.easy.design.security.service.AccessTokenService;
import com.y1ph.easy.design.security.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 认证控制器
 *
 * @author WFT
 * @since 2022/1/5
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/grant")
public class GrantController {

    private final GrantFactory factory;
    private final AccessTokenService tokenService;
    private final ClientService<? extends OauthClient<?>> clientService;
    private final CodeService codeService;

    /**
     * 登录接口
     *
     * @param parameter {@link OauthParameter} 认证参数
     * @return {@link AccessToken} 访问令牌
     */
    @PostMapping
    public AccessToken login(@RequestBody OauthParameter parameter) {
        //  客户端信息
        parameter.setClientId("");
        parameter.setClientSecret("");
        //  密码模式
        parameter.setGrantType(GrantType.password);
        //  获取访问令牌
        return this.getToken(parameter);
    }

    /**
     * 退出接口
     */
    @PreAuthorize
    @GetMapping
    public void logout() {
        this.tokenService.remove(SecurityUtil.getContext());
    }

    /**
     * 获取授权码
     *
     * @param clientId {@link String} 客户端编号
     * @return {@link String} 授权码
     */
    @PreAuthorize
    @GetMapping(value = "/code/{clientId}")
    public String getCode(@PathVariable(value = "clientId") String clientId) {
        //  获取客户端信息
        OauthClient<? extends Serializable> client = this.clientService.select(clientId);
        Assert.notNull(client, "客户端编号不存在");
        //  参数校验
        Assert.isTrue(client.getLocked().equals(Locked.enable), "客户端被锁定,请与管理员联系");
        Assert.isTrue(client.getGrantType().equals(GrantType.authorization_code), "此客户端不支持授权码模式");
        //  获取当前登录用户
        Principal<?> principal = SecurityUtil.get();
        //  生成授权码
        return this.codeService.generate(principal, client);
    }


    /**
     * 第三方应用获取获取访问令牌接口
     *
     * @param parameter {@link OauthParameter} 认证参数
     * @return {@link AccessToken} 访问令牌
     */
    @PostMapping(value = "/token")
    public AccessToken getToken(@RequestBody OauthParameter parameter) {
        //  获取当前登录用户
        Principal<?> principal = this.factory.get(parameter.getGrantType()).grant(parameter);
        //  生成访问令牌
        return this.tokenService.generate(principal, parameter.getClientId());
    }


}
