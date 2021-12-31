package com.y1ph.easy.design.common.enums;

import com.y1ph.easy.design.common.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 询问标记
 *
 * @author WFT
 * @since 2022/1/1
 */
@Getter
@AllArgsConstructor
public enum Confirm implements BaseEnum<Integer> {

    /**
     * 同意
     */
    yes(1),

    /**
     * 拒绝
     */
    not(-1);

    private final Integer value;
}
