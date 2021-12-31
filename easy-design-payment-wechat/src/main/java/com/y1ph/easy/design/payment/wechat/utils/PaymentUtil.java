package com.y1ph.easy.design.payment.wechat.utils;

import com.y1ph.easy.design.payment.beans.PaymentOrder;
import com.y1ph.easy.design.payment.beans.RefundOrder;
import com.y1ph.easy.design.payment.wechat.beans.WechatProperties;
import com.y1ph.easy.design.website.utils.HttpUtil;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Properties;

/**
 * 支付工具类
 *
 * @author WFT
 * @since 2021/12/31
 */
@SuppressWarnings("SpellCheckingInspection")
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
     * @param param     订单信息
     * @param notifyUrl 支付回调
     * @param <Param>   {@link PaymentOrder}
     * @return {@link JSONObject}
     */
    public <Param extends PaymentOrder> JSONObject buildPaymentBody(Param param, String notifyUrl) throws JSONException {
        return new JSONObject()
            //  订单编号
            .put("out_trade_no", param.getId())
            //  订单描述
            .put("description", param.getSubject())
            //  支付回调地址
            .put("notify_url", notifyUrl)
            //  订单金额信息
            .put("amount", this.getAmount(param.getPrice()));
    }

    /**
     * 构建退款的请求体
     *
     * @param param     订单信息
     * @param notifyUrl 退款通知地址
     * @param <Param>   {@link RefundOrder}
     * @return {@link JSONObject}
     */
    public <Param extends RefundOrder> JSONObject buildRefundBody(Param param, String notifyUrl) throws JSONException {
        return new JSONObject()
            //  订单编号
            .put("out_trade_no", param.getOrderId())
            //  退款单号
            .put("out_refund_no", param.getId())
            //  订单金额信息
            .put("amount", this.getAmount(param.getPrice()).put("refund", param.getRefund()))
            //  退款通知地址
            .put("notify_url", notifyUrl);
    }

    private JSONObject getAmount(Integer price) throws JSONException {
        return new JSONObject()
            .put("total", price)
            .put("currency", "CNY");
    }

    /**
     * 调用微信接口
     *
     * @param uri        除域名部分的接口地址
     * @param param      请求体
     * @param properties 微信支付相关配置
     * @param <P>        {@link WechatProperties}
     * @return {@link Properties}
     */
    public <P extends WechatProperties<?>> Properties execute(String uri, JSONObject param, P properties) throws Exception {
        //  设置应用编号和商户编号
        param.put("appid", properties.getAppId())
            .put("mchid", properties.getMerchantId());
        //  生成请求体
        String body = param.toString();
        //  生成Token
        String token = this.buildToken(uri, body, properties);
        //  设置请求头
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.ACCEPT, "application/json, text/plain, */*");
        headers.add(HttpHeaders.AUTHORIZATION, "WECHATPAY2-SHA256-RSA2048 " + token);
        //  发送请求
        return HttpUtil.getTemplate().postForObject(properties.getServerUrl() + uri, new HttpEntity<>(body, headers), Properties.class);
    }

    /**
     * 构造签名串
     *
     * @param uri        除域名部分的接口地址
     * @param body       请求体
     * @param properties 微信支付相关配置
     * @param <P>        {@link WechatProperties}
     * @return {@link String}
     */
    private <P extends WechatProperties<?>> String buildToken(String uri, String body, P properties) throws Exception {
        //  生成随机字符串和时间戳
        String str = this.getNonceStr();
        String timestamp = this.getTimestamp();
        //  签名
        String signature = this.sign(this.buildMessage("POST", uri, timestamp, str, body), properties);
        //  生成Token
        return "mchid=\"" + properties.getMerchantId() + "\","
            + "nonce_str=\"" + str + "\","
            + "timestamp=\"" + timestamp + "\","
            + "serial_no=\"" + properties.getSerialNumber() + "\","
            + "signature=\"" + signature + "\"";
    }

    /**
     * 获取时间戳
     *
     * @return {@link String}
     */
    public String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 获取随机字符串
     *
     * @return {@link String}
     */
    public String getNonceStr() {
        return String.valueOf(System.nanoTime());
    }

    /**
     * 构造签名串,每一行为一个参数,行尾以\n（换行符,ASCII编码值为0x0A）结束,包括最后一行
     *
     * @param args 参数列表
     * @return 字节数组
     */
    public byte[] buildMessage(String... args) {
        StringBuilder builder = new StringBuilder();
        for (String item : args) {
            builder.append(item).append("\n");
        }
        return builder.append("\n").toString().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 使用商户私钥对待签名串进行SHA256 with RSA签名,并对签名结果进行Base64编码得到签名值.
     *
     * @param data       数据
     * @param properties 微信支付相关配置
     * @param <P>        {@link WechatProperties}
     * @return {@link String}
     */
    public <P extends WechatProperties<?>> String sign(byte[] data, P properties) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(properties.getPrivateKey()))));
        signature.update(data);
        return Base64.getEncoder().encodeToString(signature.sign());
    }


}
