package com.y1ph.easy.design.payment.wechat.service.impl;

import com.y1ph.easy.design.payment.beans.RefundOrder;
import com.y1ph.easy.design.payment.service.RefundService;
import com.y1ph.easy.design.payment.wechat.beans.WechatProperties;
import com.y1ph.easy.design.payment.wechat.service.WechatPropertiesService;
import com.y1ph.easy.design.payment.wechat.utils.PaymentUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * 微信支付-退款接口实现类
 *
 * @author WFT
 * @since 2021/12/31
 */
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class WechatRefundServiceImpl implements RefundService {

    private final WechatPropertiesService<?> propertiesService;

    @Override
    @SneakyThrows
    public <Param extends RefundOrder> void refund(Param param, String clientId) {
        //  获取支付配置
        WechatProperties<?> properties = this.propertiesService.getProperties(clientId);
        //  构建请求体
        JSONObject body = PaymentUtil.getInstance().buildRefundBody(param,properties.getRefundNotifyUrl());
        //  API
        String api = "/v3/refund/domestic/refunds";
        //  发起退款
        PaymentUtil.getInstance().execute(api,body,properties);
    }
}
