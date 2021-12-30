package com.y1ph.easy.design.payment.alibaba.beans;

import com.y1ph.easy.design.payment.beans.PaymentProperties;

import java.io.Serializable;

/**
 * 支付宝配置
 *
 * @author WFT
 * @since 2021/12/30
 */
@SuppressWarnings("SpellCheckingInspection")
public interface AlipayProperties<Id extends Serializable> extends PaymentProperties<Id> {

    /**
     * 获取公钥
     *
     * @return {@link String}
     */
    String getPublicKey();

    /**
     * 仅支持JSON
     *
     * @return {@link String}
     */
    String getFormat();

    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     *
     * @return {@link String}
     */
    String getCharset();

    /**
     * 商户生成签名字符串所使用的签名算法类型
     * 目前支持RSA2和RSA,推荐使用RSA2
     *
     * @return {@link String}
     */
    String getSignType();

    /**
     * 获取支付中断后跳转的地址
     *
     * @return {@link String}
     */
    String getExitUrl();

    /**
     * 获取支付成功后跳转的地址
     *
     * @return {@link String}
     */
    String getSuccessUrl();

}
