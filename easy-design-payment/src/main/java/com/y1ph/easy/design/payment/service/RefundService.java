package com.y1ph.easy.design.payment.service;

import com.y1ph.easy.design.payment.beans.RefundOrder;

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
     * @param param    {@link RefundOrder} 退款订单
     * @param clientId {@link String} 客户端编号
     * @param <Param>  {@link RefundOrder}
     */
    <Param extends RefundOrder> void refund(Param param, String clientId);

}
