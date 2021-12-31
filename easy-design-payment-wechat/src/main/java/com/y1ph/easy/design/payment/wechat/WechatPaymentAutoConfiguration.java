package com.y1ph.easy.design.payment.wechat;

import com.y1ph.easy.design.payment.BasePaymentFactory;
import com.y1ph.easy.design.payment.service.RefundService;
import com.y1ph.easy.design.payment.wechat.service.BasePaymentService;
import com.y1ph.easy.design.payment.wechat.service.WechatPropertiesService;
import com.y1ph.easy.design.payment.wechat.service.impl.WechatRefundServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付自动配置类
 *
 * @author WFT
 * @since 2021/12/30
 */
@Configuration
@ComponentScan
@SuppressWarnings("SpellCheckingInspection")
public class WechatPaymentAutoConfiguration {

    /**
     * 退款接口
     *
     * @param service {@link WechatPropertiesService}
     * @return {@link RefundService}
     */
    @Bean
    public RefundService wechatRefundService(WechatPropertiesService<?> service) {
        return new WechatRefundServiceImpl(service);
    }

    /**
     * 微信支付工厂
     *
     * @param wechatRefundService 退款接口
     * @return {@link BasePaymentFactory}
     */
    @Bean
    public BasePaymentFactory<BasePaymentService> wechatPaymentFactory(RefundService wechatRefundService) {
        return new BasePaymentFactory<BasePaymentService>() {
            @Override
            public RefundService getRefund() {
                return wechatRefundService;
            }

            @Override
            public String getId() {
                return "wechat";
            }
        };
    }
}
