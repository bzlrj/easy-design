package com.y1ph.easy.design.website;

import com.y1ph.easy.design.website.context.ControllerResultHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Collections;
import java.util.List;

/**
 * 基础配置
 *
 * @author WFT
 * @since 2022/1/1
 */
@EnableAsync
@Configuration
public class BasicConfiguration implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        //  获取请求映射处理器对象
        RequestMappingHandlerAdapter adapter = context.getBean(RequestMappingHandlerAdapter.class);
        //  配置接口返回值类型处理器
        List<HandlerMethodReturnValueHandler> handlerList = adapter.getReturnValueHandlers();
        if (null != handlerList){
            handlerList.add(new ControllerResultHandler());
        } else {
            adapter.setReturnValueHandlers(Collections.singletonList(new ControllerResultHandler()));
        }
    }
}
