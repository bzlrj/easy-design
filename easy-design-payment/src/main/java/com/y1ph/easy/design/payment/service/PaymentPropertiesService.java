package com.y1ph.easy.design.payment.service;

import com.y1ph.easy.design.payment.beans.PaymentProperties;

/**
 * 支付配置接口
 *
 * @author WFT
 * @since 2021/12/30
 */
public interface PaymentPropertiesService<Properties extends PaymentProperties<?>> {

    /**
     * 获取客户端的支付配置
     *
     * @param clientId {@link String} 客户端编号
     * @return {@link PaymentProperties}
     */
    Properties getProperties(String clientId);
}
