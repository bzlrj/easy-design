package com.y1ph.easy.design.security.context;

import com.y1ph.easy.design.security.annotation.PreAuthorize;
import com.y1ph.easy.design.security.beans.Principal;
import com.y1ph.easy.design.security.beans.SecurityContext;
import com.y1ph.easy.design.security.exception.AccessDeniedException;
import com.y1ph.easy.design.security.exception.BaseSecurityException;
import com.y1ph.easy.design.security.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

/**
 * 授权处理器
 *
 * @author WFT
 * @since 2022/1/4
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizeAspectHandler {


    @Before(value = "@annotation(preAuthorize)")
    public void before(PreAuthorize preAuthorize) throws BaseSecurityException {
        //  获取上下文对象
        SecurityContext context = SecurityUtil.getContext();
        //  若上下文中存在异常,则标识未认证或令牌已超时
        if (null != context.getException()) {
            throw context.getException();
        }
        //  判断是否需要校验权限
        if (preAuthorize.value().length > 0) {
            //  权限校验
            boolean result = context.getPrincipal().getAuthorities().stream()
                .filter(StringUtils::hasText)
                .anyMatch(x -> PatternMatchUtils.simpleMatch(preAuthorize.value(), x));
            //  抛出403异常
            if (!result) {
                throw new AccessDeniedException();
            }
        }
    }

}
