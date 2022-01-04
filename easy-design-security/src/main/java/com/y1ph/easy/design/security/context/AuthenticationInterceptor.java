package com.y1ph.easy.design.security.context;

import com.y1ph.easy.design.security.beans.SecurityContext;
import com.y1ph.easy.design.security.exception.BaseSecurityException;
import com.y1ph.easy.design.security.service.TokenStorageService;
import com.y1ph.easy.design.security.utils.SecurityUtil;
import com.y1ph.easy.design.website.context.BaseHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * 认证拦截器
 *
 * @author WFT
 * @since 2022/1/4
 */
@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements BaseHandlerInterceptor {

    private final TokenStorageService storageService;
    private final AccessTokenFactory factory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //  构建权限上下文
        SecurityContext context = new SecurityContext();
        //  获取客户端编号/令牌生成规则
        context.setClientId(request.getHeader("client-id"));
        context.setTokenRule(request.getHeader("token-rule"));
        try {
            //  获取访问令牌
            context.setAccessToken(this.storageService.getAccessToken(request));
            //  获取当前用户主体信息
            context.setPrincipal(this.factory.get(context.getTokenRule()).getPrincipal(context));
        } catch (BaseSecurityException e){
            context.setException(e);
        } finally {
            SecurityUtil.set(context);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view) {
        SecurityUtil.clear();
    }

    @Override
    public List<String> getPathPatterns() {
        return Collections.singletonList("/**");
    }
}
