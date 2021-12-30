package com.y1ph.easy.design.payment.alibaba.service.impl;

import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.y1ph.easy.design.payment.alibaba.beans.AlipayProperties;
import com.y1ph.easy.design.payment.alibaba.service.BasePaymentService;
import com.y1ph.easy.design.payment.alibaba.utils.PaymentUtil;
import com.y1ph.easy.design.payment.beans.PaymentOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 支付宝App-支付实现类
 *
 * @author WFT
 * @since 2021/12/30
 */
@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class AlibabaAppPaymentServiceImpl extends BasePaymentService {

    @Override
    public <Param extends PaymentOrder> Object payment(Param param, AlipayProperties<?> properties) throws Exception {
        //  构建请求体
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //  设置支付回调地址和支付后返回的地址
        request.setNotifyUrl(properties.getNotifyUrl());
        request.setReturnUrl(properties.getSuccessUrl());
        //  设置业务参数
        request.setBizContent(PaymentUtil.getInstance().buildPaymentBody(param, "QUICK_MSECURITY_PAY").toString());
        //  调起支付
        return PaymentUtil.getInstance().execute(client -> client.sdkExecute(request),properties);
    }

    @Override
    public String getId() {
        return "app";
    }
}
