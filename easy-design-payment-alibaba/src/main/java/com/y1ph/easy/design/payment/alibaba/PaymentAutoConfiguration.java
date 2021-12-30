package com.y1ph.easy.design.payment.alibaba;

import com.y1ph.easy.design.payment.BasePaymentFactory;
import com.y1ph.easy.design.payment.alibaba.service.AlipayPropertiesService;
import com.y1ph.easy.design.payment.alibaba.service.BasePaymentService;
import com.y1ph.easy.design.payment.alibaba.service.impl.AlibabaRefundServiceImpl;
import com.y1ph.easy.design.payment.service.RefundService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝自动配置类
 *
 * @author WFT
 * @since 2021/12/30
 */
@Configuration
@ComponentScan
@SuppressWarnings("SpellCheckingInspection")
public class PaymentAutoConfiguration {

    /**
     * 退款接口
     *
     * @param service {@link AlipayPropertiesService}
     * @return {@link RefundService}
     */
    @Bean
    public RefundService alibabaRefundService(AlipayPropertiesService<?> service) {
        return new AlibabaRefundServiceImpl(service);
    }

    /**
     * 支付宝-支付工厂
     *
     * @param alibabaRefundService 退款接口
     * @return {@link BasePaymentFactory}
     */
    @Bean
    public BasePaymentFactory<BasePaymentService> alibabaPaymentFactory(RefundService alibabaRefundService) {
        return new BasePaymentFactory<BasePaymentService>() {
            @Override
            public RefundService getRefund() {
                return alibabaRefundService;
            }

            @Override
            public String getId() {
                return "alipay";
            }
        };
    }

}
