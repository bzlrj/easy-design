package com.y1ph.easy.design.payment.wechat.service;

import com.y1ph.easy.design.payment.beans.PaymentOrder;
import com.y1ph.easy.design.payment.service.PaymentService;
import com.y1ph.easy.design.payment.wechat.beans.WechatProperties;
import com.y1ph.easy.design.payment.wechat.beans.WechatResponse;
import com.y1ph.easy.design.payment.wechat.utils.PaymentUtil;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.function.Supplier;

/**
 * 微信支付接口抽象类
 *
 * @author WFT
 * @since 2021/12/31
 */
public abstract class BasePaymentService implements PaymentService<WechatProperties<?>>, ApplicationContextAware {

    private WechatPropertiesService<?> propertiesService;

    @Override
    @SneakyThrows
    public <Param extends PaymentOrder> Object payment(Param param, String clientId) {
        return this.payment(param, this.propertiesService.getProperties(clientId));
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.propertiesService = context.getBean(WechatPropertiesService.class);
    }

    /**
     * 构建客户端唤醒支付时的所需参数
     *
     * @param prepayId   交易会话的标识
     * @param properties 微信支付相关配置
     * @param supplier   {@link Supplier}
     * @param <P>        {@link WechatProperties}
     * @return {@link WechatResponse}
     */
    @SuppressWarnings("SpellCheckingInspection")
    protected <P extends WechatProperties<?>> WechatResponse build(String prepayId, P properties, Supplier<String> supplier) throws Exception {
        //  时间戳和随机字符串
        String timestamp = PaymentUtil.getInstance().getTimestamp();
        String str = PaymentUtil.getInstance().getNonceStr();
        //  构造签名串
        byte[] data = PaymentUtil.getInstance().buildMessage(properties.getAppId(), timestamp, str, supplier.get());
        //  签名
        String signature = PaymentUtil.getInstance().sign(data, properties);
        //  构建响应体
        return WechatResponse.builder().appId(properties.getAppId())
            .merchantId(properties.getMerchantId())
            .timestamp(timestamp)
            .nonceStr(str)
            .prepayId(prepayId)
            .signature(signature)
            .build();
    }

}
