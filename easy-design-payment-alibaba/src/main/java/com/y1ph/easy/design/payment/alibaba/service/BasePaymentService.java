package com.y1ph.easy.design.payment.alibaba.service;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.y1ph.easy.design.common.util.Function;
import com.y1ph.easy.design.payment.alibaba.beans.AlipayProperties;
import com.y1ph.easy.design.payment.beans.OrderParam;
import com.y1ph.easy.design.payment.service.PaymentService;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 支付接口抽象类
 *
 * @author WFT
 * @since 2021/12/30
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class BasePaymentService<Response extends AlipayResponse> implements PaymentService, ApplicationContextAware {

    private AlipayPropertiesService<?> propertiesService;

    /**
     * 调起第三方支付
     *
     * @param function     {@link Function}
     * @param properties   {@link AlipayProperties} 支付配置
     * @param <Properties> {@link AlipayProperties}
     * @return {@link String}
     */
    @SneakyThrows
    protected <Properties extends AlipayProperties<?>> String execute(Function<AlipayClient, Response> function, Properties properties) {
        return function.apply(new DefaultAlipayClient(
            properties.getServerUrl(),
            properties.getAppId(),
            properties.getPrivateKey(),
            properties.getFormat(),
            properties.getCharset(),
            properties.getPublicKey(),
            properties.getSignType()
        )).getBody();
    }

    @Override
    public <Param extends OrderParam> Object payment(Param param, String clientId) {
        return this.payment(param, this.propertiesService.getProperties(clientId));
    }

    /**
     * 调起第三方支付
     *
     * @param param      {@link OrderParam} 订单参数
     * @param properties {@link AlipayProperties} 支付配置
     * @param <Param>    {@link OrderParam}
     * @return {@link Object}
     */
    protected abstract <Param extends OrderParam> Object payment(Param param, AlipayProperties<?> properties);

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        //  获取支付宝配置接口
        this.propertiesService = context.getBean(AlipayPropertiesService.class);
        if (null == this.propertiesService) {
            throw new RuntimeException(String.format("请实现%s接口", AlipayPropertiesService.class.toString()));
        }
    }

}
