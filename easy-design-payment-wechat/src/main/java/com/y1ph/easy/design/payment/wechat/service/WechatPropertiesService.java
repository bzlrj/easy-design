package com.y1ph.easy.design.payment.wechat.service;

import com.y1ph.easy.design.payment.service.PaymentPropertiesService;
import com.y1ph.easy.design.payment.wechat.beans.WechatProperties;

/**
 * 微信支付配置接口
 *
 * @author WFT
 * @since 2021/12/31
 */
@SuppressWarnings(value = "SpellCheckingInspection")
public interface WechatPropertiesService<Properties extends WechatProperties<?>> extends PaymentPropertiesService<Properties> {

    
}
