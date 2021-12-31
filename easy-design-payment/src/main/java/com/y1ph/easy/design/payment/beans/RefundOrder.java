package com.y1ph.easy.design.payment.beans;

import com.y1ph.easy.design.common.BaseEntity;

/**
 * 退款订单接口
 *
 * @author WFT
 * @since 2021/12/30
 */
public interface RefundOrder extends BaseEntity<Long> {

    /**
     * 获取支付订单的编号
     *
     * @return {@link Long}
     */
    Long getOrderId();

    /**
     * 获取原订单金额
     *
     * @return {@link Integer}
     */
    Integer getPrice();

    /**
     * 获取退款金额
     *
     * @return {@link Integer}
     */
    Integer getRefund();

}
