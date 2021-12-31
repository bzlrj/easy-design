package com.y1ph.easy.design.common.enums;

import com.y1ph.easy.design.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别标记
 *
 * @author WFT
 * @since 2022/1/1
 */
@Getter
@AllArgsConstructor
public enum Gender implements BaseEnum<Integer> {

    /**
     * 男
     */
    man(1),

    /**
     * 女
     */
    woman(2),

    /**
     * 未知
     */
    unknown(-1);

    private final Integer value;

}
