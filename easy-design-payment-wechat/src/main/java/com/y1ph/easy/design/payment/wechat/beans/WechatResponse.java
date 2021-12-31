package com.y1ph.easy.design.payment.wechat.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 微信支付返回值类型
 *
 * @author WFT
 * @since 2021/12/31
 */
@Getter
@Setter
@SuppressWarnings("SpellCheckingInspection")
public class WechatResponse implements Serializable {

    /**
     * 预支付交易会话标识,用于后续接口调用中使用，该值有效期为2小时.
     * 使用场景：【App支付,JsApi支付】
     */
    @JsonProperty(value = "prepay_id")
    private String prepayId;

    /**
     * 拉起微信支付收银台的中间页面,可通过访问该url来拉起微信客户端,完成支付,h5_url的有效期为5分钟.
     * 使用场景：【H5支付】
     */
    @JsonProperty(value = "h5_url")
    private String h5Url;

    /**
     * 此URL用于生成支付二维码,然后提供给用户扫码支付.
     * 使用场景：【Native支付】
     */
    @JsonProperty(value = "code_url")
    private String codeUrl;

    /**
     * 异常信息
     */
    @JsonProperty(value = "message")
    private String message;

}
