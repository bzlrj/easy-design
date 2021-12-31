package com.y1ph.easy.design.payment.wechat.service.impl;

import com.y1ph.easy.design.payment.beans.PaymentOrder;
import com.y1ph.easy.design.payment.wechat.beans.WechatProperties;
import com.y1ph.easy.design.payment.wechat.service.BasePaymentService;
import com.y1ph.easy.design.payment.wechat.utils.PaymentUtil;
import com.y1ph.easy.design.website.utils.WebUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * 微信H5-支付实现类
 *
 * @author WFT
 * @since 2021/12/31
 */
@Service
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
public class WechatH5PaymentServiceImpl extends BasePaymentService {

    @Override
    public <Param extends PaymentOrder> Object payment(Param param, WechatProperties<?> properties) throws Exception {
        //  构建请求体
        JSONObject body = PaymentUtil.getInstance().buildPaymentBody(param, properties.getNotifyUrl());
        //  配置支付场景信息
        JSONObject sceneInfo = new JSONObject()
            .put("payer_client_ip", WebUtil.getInstance().getIp())
            .put("h5_info",new JSONObject().put("type","Wap"));
        body.put("scene_info",sceneInfo);
        //  API
        String api = "/v3/pay/transactions/h5";
        //  调起支付得到一个地址,客户端通过跳转到此地址,唤醒支付
        return PaymentUtil.getInstance().execute(api,body,properties).getProperty("h5_url");
    }

    @Override
    public String getId() {
        return "h5";
    }
}
