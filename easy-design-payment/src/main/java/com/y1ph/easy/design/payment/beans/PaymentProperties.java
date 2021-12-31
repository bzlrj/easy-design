package com.y1ph.easy.design.payment.beans;

import com.y1ph.easy.design.common.BaseEntity;

import java.io.Serializable;

/**
 * 支付配置参数-公共接口
 *
 * @author WFT
 * @since 2021/12/30
 */
public interface PaymentProperties<Id extends Serializable> extends BaseEntity<Id> {

    /**
     * 获取客户端编号
     *
     * @return {@link String}
     */
    String getClientId();

    /**
     * 获取应用编号
     *
     * @return {@link String}
     */
    String getAppId();

    /**
     * 获取私钥
     *
     * @return {@link String}
     */
    String getPrivateKey();

    /**
     * 获取用于接收支付通知的接口地址
     *
     * @return {@link String}
     */
    String getNotifyUrl();

    /**
     * 获取第三方支付的服务地址
     *
     * @return {@link String}
     */
    String getServerUrl();

    /**
     * 获取用于接收退款通知的接口地址
     *
     * @return {@link String}
     */
    String getRefundNotifyUrl();

}
