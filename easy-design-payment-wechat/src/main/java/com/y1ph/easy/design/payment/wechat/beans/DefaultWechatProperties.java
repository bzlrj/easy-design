package com.y1ph.easy.design.payment.wechat.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信支付默认配置
 *
 * @author WFT
 * @since 2021/12/31
 */
@Getter
@Setter
public class DefaultWechatProperties implements WechatProperties<Long> {

    /**
     * 主键
     */
    private Long id;

    /**
     * 客户端编号
     */
    private String clientId;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 商户序列号
     */
    private String serialNumber;

    /**
     * 应用编号
     */
    private String appId;

    /**
     * 密钥
     */
    private String privateKey;

    /**
     * 支付后用于接受第三方异步通知的接口地址
     */
    private String notifyUrl;

    /**
     * 第三方支付的服务地址
     */
    private String serverUrl = "https://api.mch.weixin.qq.com";

}
