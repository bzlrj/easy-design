package com.y1ph.easy.design.payment.beans;

import com.y1ph.easy.design.common.BaseEntity;

/**
 * 订单参数接口
 *
 * @author WFT
 * @since 2021/12/30
 */
public interface OrderParam extends BaseEntity<Long> {

    /**
     * 获取订单主题
     *
     * @return {@link String}
     */
    String getSubject();

    /**
     * 获取应付金额
     *
     * @return {@link Integer}
     */
    Integer getPrice();

}
