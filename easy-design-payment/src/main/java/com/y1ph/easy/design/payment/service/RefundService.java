package com.y1ph.easy.design.payment.service;

import com.y1ph.easy.design.payment.beans.OrderParam;

/**
 * 退款接口
 *
 * @author WFT
 * @since 2021/12/30
 */
public interface RefundService {

    /**
     * 调用第三方的退款接口
     *
     * @param param {@link OrderParam} 订单参数
     * @param <T>   {@link OrderParam} 订单类
     */
    <T extends OrderParam> void refund(T param);

}
