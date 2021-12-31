package com.y1ph.easy.design.website;

import com.y1ph.easy.design.website.context.BaseHandlerInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collection;

/**
 * WebMvc 配置类
 *
 * @author WFT
 * @since 2021/12/31
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer, ApplicationContextAware {

    /**
     * 拦截器列表
     */
    private Collection<BaseHandlerInterceptor> interceptors = null;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        interceptors.forEach(interceptor -> registry.addInterceptor(interceptor).addPathPatterns(interceptor.getPathPatterns()));
    }


    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        //  从容器中获取拦截器
        this.interceptors = context.getBeansOfType(BaseHandlerInterceptor.class).values();
    }
}
