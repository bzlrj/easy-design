package com.y1ph.easy.design.payment;

import com.y1ph.easy.design.common.BaseBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 支付工厂,根据传入的参数获取到某个支付厂商
 *
 * @author WFT
 * @since 2021/12/30
 */
@Component
public class PaymentFactory extends BaseBeanFactory<String, BasePaymentFactory<?>> {

    @Override
    protected IllegalArgumentException illegalArgumentException() {
        return new IllegalArgumentException("未实现此支付接口");
    }

}
