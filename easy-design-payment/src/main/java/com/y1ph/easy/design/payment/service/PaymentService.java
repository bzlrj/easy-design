package com.y1ph.easy.design.payment.service;

import com.y1ph.easy.design.common.BaseBean;
import com.y1ph.easy.design.payment.beans.OrderParam;

/**
 * 支付接口
 *
 * @author WFT
 * @since 2021/12/30
 */
public interface PaymentService extends BaseBean<String> {

    /**
     * 调起第三方支付
     *
     * @param param    {@link OrderParam} 订单参数
     * @param clientId {@link String} 客户端编号
     * @param <Param>  {@link OrderParam} 订单类
     * @return {@link Object} 不同方式的支付返回值不同,因此这里返回一个Object
     */
    <Param extends OrderParam> Object payment(Param param, String clientId);

}
