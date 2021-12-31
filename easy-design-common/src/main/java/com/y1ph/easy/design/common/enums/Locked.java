package com.y1ph.easy.design.common.enums;

import com.y1ph.easy.design.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 锁定标记
 *
 * @author WFT
 * @since 2022/1/1
 */
@Getter
@AllArgsConstructor
public enum Locked implements BaseEnum<Integer> {

    /**
     * 启用
     */
    enable(0),

    /**
     * 锁定中
     */
    locking(-1);

    private final Integer value;
}
