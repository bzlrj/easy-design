package com.y1ph.easy.design.payment.alibaba.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.y1ph.easy.design.common.utils.Function;
import com.y1ph.easy.design.payment.alibaba.beans.AlipayProperties;
import com.y1ph.easy.design.payment.beans.PaymentOrder;
import com.y1ph.easy.design.payment.beans.RefundOrder;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * 支付工具类
 *
 * @author WFT
 * @since 2021/12/30
 */
public class PaymentUtil {

    /**
     * 私有化构造函数
     */
    private PaymentUtil() {

    }

    /**
     * 以静态内部类实例化当前对象,从而达到懒汉式单例的效果
     */
    private static class Holder {

        private final static PaymentUtil INSTANCE = new PaymentUtil();

    }

    /**
     * 获取当前实例对象
     *
     * @return {@link PaymentUtil}
     */
    public static PaymentUtil getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 构建支付的请求体
     *
     * @param param   订单信息
     * @param code    业务编码
     * @param <Param> {@link PaymentOrder}
     * @return {@link JSONObject}
     */
    public <Param extends PaymentOrder> JSONObject buildPaymentBody(Param param, String code) throws JSONException {
        return new JSONObject()
            .put("out_trade_no", param.getId())
            .put("total_amount", this.priceFormat(param.getPrice()))
            .put("subject", param.getSubject())
            .put("product_code", code);
    }

    /**
     * 构建退款的请求体
     *
     * @param param   订单信息
     * @param <Param> {@link RefundOrder}
     * @return {@link JSONObject}
     */
    public <Param extends RefundOrder> JSONObject buildRefundBody(Param param) throws JSONException {
        return new JSONObject()
            .put("out_trade_no", param.getOrderId())
            .put("refund_amount", this.priceFormat(param.getRefund()))
            .put("out_request_no",param.getId());
    }

    private double priceFormat(Integer price) {
        return ((double) price) / 100;
    }

    /**
     * 创建支付宝客户端
     *
     * @param properties 支付配置
     * @param <P>        {@link AlipayProperties}
     * @return {@link AlipayClient}
     */
    @SuppressWarnings("SpellCheckingInspection")
    private <P extends AlipayProperties<?>> AlipayClient create(P properties) {
        return new DefaultAlipayClient(
            properties.getServerUrl(),
            properties.getAppId(),
            properties.getPrivateKey(),
            properties.getFormat(),
            properties.getCharset(),
            properties.getPublicKey(),
            properties.getSignType()
        );
    }

    /**
     * 创建支付宝客户端,并调用接口
     *
     * @param callback   {@link Function} 回调函数
     * @param properties 支付配置
     * @param <R>        {@link AlipayResponse}
     * @param <P>        {@link AlipayProperties}
     * @return {@link String}
     * @throws Exception 异常
     */
    @SuppressWarnings("SpellCheckingInspection")
    public <R extends AlipayResponse, P extends AlipayProperties<?>> String execute(Function<AlipayClient, R> callback, P properties) throws Exception {
        R response = callback.apply(this.create(properties));
        if (response.isSuccess()) {
            return response.getBody();
        }
        throw new RuntimeException("接口调用失败");
    }

}
