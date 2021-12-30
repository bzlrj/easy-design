package com.y1ph.easy.design.payment.alibaba.service;

import com.y1ph.easy.design.payment.alibaba.beans.AlipayProperties;
import com.y1ph.easy.design.payment.beans.OrderParam;
import com.y1ph.easy.design.payment.service.PaymentService;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 支付接口抽象类
 *
 * @author WFT
 * @since 2021/12/30
 */
public abstract class BasePaymentService implements PaymentService<AlipayProperties<?>>, ApplicationContextAware {

    private AlipayPropertiesService<?> propertiesService;

    @Override
    @SneakyThrows
    public <Param extends OrderParam> Object payment(Param param, String clientId) {
        return this.payment(param, this.propertiesService.getProperties(clientId));
    }

    /**
     * 构建业务参数
     *
     * @param param   订单信息
     * @param code    业务编号
     * @param <Param> {@link OrderParam}
     * @return {@link JSONObject}
     */
    protected <Param extends OrderParam> JSONObject build(Param param, String code) throws JSONException {
        return new JSONObject()
            .put("out_trade_no", param.getId())
            .put("total_amount", ((double) param.getPrice()) / 100)
            .put("subject", param.getSubject())
            .put("product_code", code);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        //  获取支付宝配置接口
        this.propertiesService = context.getBean(AlipayPropertiesService.class);
        if (null == this.propertiesService) {
            throw new RuntimeException(String.format("请实现%s接口", AlipayPropertiesService.class.toString()));
        }
    }

}
