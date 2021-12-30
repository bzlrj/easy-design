package com.y1ph.easy.design.payment.service;

import com.y1ph.easy.design.payment.beans.PaymentProperties;

import java.util.List;

/**
 * 支付配置接口
 *
 * @author WFT
 * @since 2021/12/30
 */
public interface PaymentPropertiesService<P extends PaymentProperties<?>> {

    /**
     * 获取客户端的支付配置
     *
     * @param clientId {@link String} 客户端编号
     * @return {@link PaymentProperties}
     */
    P getProperties(String clientId);

    /**
     * 从配置列表中,获取指定客户端的支付配置
     *
     * @param clientId {@link String} 客户端编号
     * @param list     {@link List} 配置列表
     * @return {@link PaymentProperties}
     */
    default P getProperties(String clientId, List<P> list) {
        for (P item : list) {
            if (item.getClientId().equals(clientId)) {
                return item;
            }
        }
        throw new IllegalArgumentException(String.format("请到配置文件中为当前客户端添加支付相关参数![clientId=%s]", clientId));
    }
}
