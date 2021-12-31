package com.y1ph.easy.design.website.context;

import com.y1ph.easy.design.common.BaseBeanFactory;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 异常处理器工厂
 *
 * @author WFT
 * @since 2022/1/1
 */
@Component
public class ExceptionHandlerFactory extends BaseBeanFactory<Class<? extends Throwable>, ExceptionHandler<Class<? extends Throwable>>> implements HandlerExceptionResolver {

    @Override
    protected IllegalArgumentException illegalArgumentException() {
        return new IllegalArgumentException("未定义此异常处理器");
    }

    @Override
    public ExceptionHandler<Class<? extends Throwable>> get(Class<? extends Throwable> clazz) {
        try {
            //  调用父类的方法
            return super.get(clazz);
        }catch (IllegalArgumentException e){
            //  获取父类
            Class<?> superclass = clazz.getSuperclass();
            if (superclass.equals(Object.class)){
                throw e;
            }
            //noinspection unchecked
            return this.get((Class<? extends Throwable>) superclass);
        }
    }

    @Override
    @SneakyThrows
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(this.get(ex.getClass()).resolve(ex));
        printWriter.close();
        return new ModelAndView();
    }
}
