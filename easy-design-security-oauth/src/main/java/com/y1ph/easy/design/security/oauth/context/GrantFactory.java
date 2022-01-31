package com.y1ph.easy.design.security.oauth.context;

import com.y1ph.easy.design.common.BaseBeanFactory;
import com.y1ph.easy.design.security.oauth.enums.GrantType;
import com.y1ph.easy.design.security.oauth.service.GrantService;
import org.springframework.stereotype.Component;

/**
 * 认证工厂
 *
 * @author WFT
 * @since 2022/1/9
 */
@Component
public class GrantFactory extends BaseBeanFactory<GrantType, GrantService> {

    @Override
    protected IllegalArgumentException illegalArgumentException() {
        return new IllegalArgumentException("");
    }

}
