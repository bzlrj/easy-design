package com.y1ph.easy.design.payment.alibaba.service.impl;

import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.y1ph.easy.design.payment.alibaba.beans.AlipayProperties;
import com.y1ph.easy.design.payment.alibaba.service.BasePaymentService;
import com.y1ph.easy.design.payment.beans.OrderParam;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * 支付宝Pc-支付实现类
 *
 * @author WFT
 * @since 2021/12/30
 */
@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class AlibabaPcPaymentServiceImpl extends BasePaymentService<AlipayTradePagePayResponse> {

    @Override
    public <Param extends OrderParam> Object payment(Param param, AlipayProperties<?> properties) throws Exception {
        //  构建请求体
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //  设置支付回调地址和支付后返回的地址
        request.setNotifyUrl(properties.getNotifyUrl());
        request.setReturnUrl(properties.getSuccessUrl());
        //  设置业务参数
        JSONObject content = this.build(param, "FAST_INSTANT_TRADE_PAY")
            .put("request_from_url", properties.getExitUrl());
        request.setBizContent(content.toString());
        //  调起支付
        return super.execute(client -> client.pageExecute(request), properties);
    }

    @Override
    public String getId() {
        return "pc";
    }

}
