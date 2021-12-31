package com.y1ph.easy.design.website.context;

import com.y1ph.easy.design.common.utils.JsonUtil;
import com.y1ph.easy.design.website.annotation.IgnoreResponseBody;
import com.y1ph.easy.design.website.beans.ResultBean;
import com.y1ph.easy.design.website.constant.HttpStatus;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 接口返回值处理器
 *
 * @author WFT
 * @since 2022/1/1
 */
public class ControllerResultHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter parameter) {
        if (null != parameter.getMethodAnnotation(IgnoreResponseBody.class)){
            return false;
        }
        Class<?> clazz = parameter.getContainingClass();
        return clazz.isAnnotationPresent(RestController.class)
            || clazz.isAnnotationPresent(ResponseBody.class)
            || null != parameter.getMethodAnnotation(ResponseBody.class);
    }

    @Override
    public void handleReturnValue(Object result, MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest request) throws IOException {
        // 标识请求是否已经在该方法内完成处理
        container.setRequestHandled(true);
        //  打印返回值
        HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
        if (null != response) {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(ResultBean.build(HttpStatus.OK, null, result));
            printWriter.close();
        }
    }
}
