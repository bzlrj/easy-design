package com.y1ph.easy.design.payment;

import com.y1ph.easy.design.common.BaseBean;
import com.y1ph.easy.design.common.BaseBeanFactory;
import com.y1ph.easy.design.payment.service.PaymentService;
import com.y1ph.easy.design.payment.service.RefundService;

/**
 * 所有支付厂商的公共父类
 * 通过此类方法可获取到支付,退款等接口的具体实现对象
 *
 * @author WFT
 * @since 2021/12/30
 */
public abstract class BasePaymentFactory<Payment extends PaymentService<?>>
    extends BaseBeanFactory<String, Payment> implements BaseBean<String> {

    @Override
    protected IllegalArgumentException illegalArgumentException() {
        return new IllegalArgumentException("暂不支持此接口");
    }

    /**
     * 根据参数获取支付接口，例如：【H5支付,App支付,扫码支付,刷脸支付.....】
     *
     * @param param {@link String}
     * @return {@link PaymentService}
     */
    public Payment getPayment(String param) {
        return super.get(param);
    }


    /**
     * 获取退款接口
     *
     * @return {@link RefundService}
     */
    public abstract RefundService getRefund();

}
