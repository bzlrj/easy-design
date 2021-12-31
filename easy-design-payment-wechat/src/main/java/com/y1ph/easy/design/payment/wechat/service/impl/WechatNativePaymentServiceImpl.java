package com.y1ph.easy.design.payment.wechat.service.impl;

import com.y1ph.easy.design.payment.beans.PaymentOrder;
import com.y1ph.easy.design.payment.wechat.beans.WechatProperties;
import com.y1ph.easy.design.payment.wechat.service.BasePaymentService;
import com.y1ph.easy.design.payment.wechat.utils.PaymentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * 微信Native-支付实现类
 *
 * @author WFT
 * @since 2021/12/31
 */
@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class WechatNativePaymentServiceImpl extends BasePaymentService {

    @Override
    public <Param extends PaymentOrder> Object payment(Param param, WechatProperties<?> properties) throws Exception {
        //  构建请求体
        JSONObject body = PaymentUtil.getInstance().buildPaymentBody(param, properties.getNotifyUrl());
        //  API
        String api = "/v3/pay/transactions/native";
        //  调起支付得到一个地址,客户端通过跳转到此地址,唤醒支付
        return PaymentUtil.getInstance().execute(api,body,properties).getProperty("code_url");
    }

    @Override
    public String getId() {
        return "native";
    }

}
