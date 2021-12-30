package com.y1ph.easy.design.payment.wechat.service;

import com.y1ph.easy.design.payment.beans.PaymentOrder;
import com.y1ph.easy.design.payment.service.PaymentService;
import com.y1ph.easy.design.payment.wechat.beans.WechatProperties;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 微信支付接口抽象类
 *
 * @author WFT
 * @since 2021/12/31
 */
public abstract class BasePaymentService implements PaymentService<WechatProperties<?>>, ApplicationContextAware {

    private WechatPropertiesService<?> propertiesService;

    @Override
    @SneakyThrows
    public <Param extends PaymentOrder> Object payment(Param param, String clientId) {
        return this.payment(param,this.propertiesService.getProperties(clientId));
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.propertiesService = context.getBean(WechatPropertiesService.class);
    }
}
