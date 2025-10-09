package com.kevin.config.intercepter;


import com.kevin.context.BaseContext;
import com.kevin.util.GlobalFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Long loginUserId = GlobalFn.extractId(request.getHeader("token"));
//        设置global token id 在 ThreadLocal

        System.out.println("interceptor:" + loginUserId);
        BaseContext.setTokenId(loginUserId);
        return true;
    }


}
