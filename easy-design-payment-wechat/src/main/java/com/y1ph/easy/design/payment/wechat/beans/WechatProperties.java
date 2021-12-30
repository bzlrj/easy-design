package com.y1ph.easy.design.payment.wechat.beans;

import com.y1ph.easy.design.payment.beans.PaymentProperties;

import java.io.Serializable;

/**
 * 微信配置
 *
 * @author WFT
 * @since 2021/12/31
 */
public interface WechatProperties<Id extends Serializable> extends PaymentProperties<Id> {

    /**
     * 获取商户编号
     *
     * @return {@link String}
     */
    String getMerchantId();

    /**
     * 获取商户序列号
     *
     * @return {@link String}
     */
    String getSerialNumber();

}
