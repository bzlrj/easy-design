package com.y1ph.easy.design.payment.alibaba.service;

import com.y1ph.easy.design.payment.alibaba.beans.AlipayProperties;
import com.y1ph.easy.design.payment.service.PaymentPropertiesService;

/**
 * 支付宝配置接口
 *
 * @author WFT
 * @since 2021/12/30
 */
@SuppressWarnings(value = "SpellCheckingInspection")
public interface AlipayPropertiesService<Properties extends AlipayProperties<?>> extends PaymentPropertiesService<Properties> {

}
