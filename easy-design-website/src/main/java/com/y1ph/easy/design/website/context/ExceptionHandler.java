package com.y1ph.easy.design.website.context;

import com.y1ph.easy.design.common.BaseBean;
import com.y1ph.easy.design.website.beans.ResultBean;

/**
 * 异常处理器接口
 *
 * @author WFT
 * @since 2022/1/1
 */
public interface ExceptionHandler<T extends Class<? extends Throwable>> extends BaseBean<T> {

    /**
     * 解决异常
     *
     * @param exception {@link Exception}
     * @return {@link ResultBean}
     */
    <E extends Throwable> ResultBean<?> resolve(E exception);

}
