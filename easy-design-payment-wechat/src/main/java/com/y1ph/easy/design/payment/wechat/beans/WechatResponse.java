package com.y1ph.easy.design.payment.wechat.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 微信支付返回值类型
 *
 * @author WFT
 * @since 2021/12/31
 */
@Getter
@Setter
@Builder
@SuppressWarnings("SpellCheckingInspection")
public class WechatResponse implements Serializable {

    /**
     * 应用编号
     */
    private String appId;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 预支付交易会话标识,用于后续接口调用中使用，该值有效期为2小时.
     * 使用场景：【App支付,JsApi支付】
     */
    private String prepayId;

    /**
     * 签名
     */
    private String signature;

}
