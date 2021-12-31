package com.y1ph.easy.design.common.enums;

import com.y1ph.easy.design.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 流程标记
 *
 * @author WFT
 * @since 2022/1/1
 */
@Getter
@AllArgsConstructor
public enum Process implements BaseEnum<Integer> {

    /**
     * 初始状态
     */
    init(0),

    /**
     * 等待处理中
     */
    wait(1),

    /**
     * 处理结果：成功
     */
    success(2),

    /**
     * 处理结果：失败
     */
    fail(3);


    private final Integer value;
}
