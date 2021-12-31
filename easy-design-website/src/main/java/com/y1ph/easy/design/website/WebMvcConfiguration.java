package com.y1ph.easy.design.website;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Mvc 配置类
 *
 * @author WFT
 * @since 2021/12/31
 */
@Configuration
public class WebMvcConfiguration implements ApplicationContextAware {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addInterceptors(InterceptorRegistry registry) {

            }
        };
    }


    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {

    }
}
