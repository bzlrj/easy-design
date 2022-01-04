package com.y1ph.easy.design.security.context;

import com.y1ph.easy.design.common.BaseBeanFactory;
import com.y1ph.easy.design.security.service.AccessTokenService;
import org.springframework.stereotype.Component;

/**
 * 访问令牌接口工厂
 *
 * @author WFT
 * @since 2022/1/4
 */
@Component
public class AccessTokenFactory extends BaseBeanFactory<String, AccessTokenService> {

    @Override
    protected IllegalArgumentException illegalArgumentException() {
        return new IllegalArgumentException("访问令牌生成规则不存在");
    }

}
