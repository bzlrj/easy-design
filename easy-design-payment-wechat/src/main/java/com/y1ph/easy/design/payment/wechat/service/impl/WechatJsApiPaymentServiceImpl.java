package com.y1ph.easy.design.payment.wechat.service.impl;

import com.y1ph.easy.design.payment.beans.PaymentOrder;
import com.y1ph.easy.design.payment.wechat.beans.WechatProperties;
import com.y1ph.easy.design.payment.wechat.service.BasePaymentService;
import com.y1ph.easy.design.payment.wechat.utils.PaymentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * 微信JsApi-支付实现类
 *
 * @author WFT
 * @since 2021/12/31
 */
@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class WechatJsApiPaymentServiceImpl extends BasePaymentService {

    @Override
    public <Param extends PaymentOrder> Object payment(Param param, WechatProperties<?> properties) throws Exception {
        //  构建请求体
        JSONObject body = PaymentUtil.getInstance().buildPaymentBody(param, properties.getNotifyUrl());
        //  配置支付者信息
        body.put("payer",new JSONObject().put("openid",param.getOpenId()));
        //  API
        String api = "/v3/pay/transactions/jsapi";
        //  调起支付后,获取到交易会话的标识
        String prepayId = PaymentUtil.getInstance().execute(api, body, properties).getProperty("prepay_id");
        //  构建客户端唤醒支付时的所需参数
        return this.build(prepayId,properties,() -> "prepay_id=" + prepayId);
    }

    @Override
    public String getId() {
        return "jsapi";
    }
}
