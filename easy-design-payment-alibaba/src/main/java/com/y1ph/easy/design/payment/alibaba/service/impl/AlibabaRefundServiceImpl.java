package com.y1ph.easy.design.payment.alibaba.service.impl;

import com.alipay.api.request.AlipayTradeRefundRequest;
import com.y1ph.easy.design.payment.alibaba.beans.AlipayProperties;
import com.y1ph.easy.design.payment.alibaba.service.AlipayPropertiesService;
import com.y1ph.easy.design.payment.alibaba.utils.PaymentUtil;
import com.y1ph.easy.design.payment.beans.RefundOrder;
import com.y1ph.easy.design.payment.service.RefundService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * 支付宝-退款接口实现类
 *
 * @author WFT
 * @since 2021/12/30
 */
@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class AlibabaRefundServiceImpl implements RefundService {

    private final AlipayPropertiesService<?> propertiesService;

    @Override
    @SneakyThrows
    public <Param extends RefundOrder> void refund(Param param, String clientId) {
        //  获取支付配置
        AlipayProperties<?> properties = this.propertiesService.getProperties(clientId);
        //  构建请求体
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        //  设置业务参数
        request.setBizContent(PaymentUtil.getInstance().buildRefundBody(param).toString());
        //  调用退款接口
        PaymentUtil.getInstance().execute(client -> client.execute(request),properties);
    }
}
