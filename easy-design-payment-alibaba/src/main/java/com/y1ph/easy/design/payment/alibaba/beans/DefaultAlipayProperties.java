package com.y1ph.easy.design.payment.alibaba.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * 支付宝默认配置
 *
 * @author WFT
 * @since 2021/12/30
 */
@Getter
@Setter
@SuppressWarnings("SpellCheckingInspection")
public class DefaultAlipayProperties implements AlipayProperties<Long> {

    /**
     * 主键
     */
    private Long id;

    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 应用编号
     */
    private String appId;

    /**
     * 密钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 支付后用于接受第三方异步通知的接口地址
     */
    private String notifyUrl;

    /**
     * 第三方支付的服务地址
     */
    private String serverUrl = "https://openapi.alipay.com/gateway.do";

    /**
     * 仅支持JSON
     */
    private final String format = "json";

    /**
     * 请求使用的编码格式
     */
    private String charset = "utf-8";

    /**
     * 商户生成签名字符串所使用的签名算法类型
     */
    private String signType = "RSA2";

    /**
     * 支付中断后跳转的地址
     */
    private String exitUrl;

    /**
     * 支付成功后跳转的地址
     */
    private String successUrl;

}
