package com.y1ph.easy.design.security;

import com.y1ph.easy.design.security.service.TokenStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 权限自动化配置
 *
 * @author WFT
 * @since 2022/1/4
 */
@ComponentScan
@Configuration
public class SecurityAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(value = TokenStorageService.class)
    public TokenStorageService tokenStorageService() {
        return new TokenStorageService() {

        };
    }

}
