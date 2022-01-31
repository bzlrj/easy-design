package com.y1ph.easy.design.security.oauth.context;

import com.y1ph.easy.design.common.BaseBeanFactory;
import com.y1ph.easy.design.security.oauth.service.UserService;
import org.springframework.stereotype.Component;

/**
 * 用户工厂
 *
 * @author WFT
 * @since 2022/1/6
 */
@Component
public class UserFactory extends BaseBeanFactory<String, UserService<?>> {

    @Override
    protected IllegalArgumentException illegalArgumentException() {
        return new IllegalArgumentException("客户端编号有误");
    }

}
