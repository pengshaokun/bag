package com.zhskg.bag.rest.intercepter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zhskg.bag.param.AuthResponse;
import com.zhskg.bag.param.LoginInfo;
import com.zhskg.bag.rest.utils.LoginUserThreadLocal;
import com.zhskg.bag.service.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class AuthorizeInterceptor implements HandlerInterceptor {

    @Reference(version = "1.0")
    private IAuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        httpServletResponse.setCharacterEncoding("utf-8");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            AllowAnonymous allowAnonymous = method.getAnnotation(AllowAnonymous.class);
            if (allowAnonymous != null) {
                return true;//true的意思是可以继续执行，false是执行中断。
            } else {
                String token = httpServletRequest.getHeader("token");
                // 1、拦截器认证
                AuthResponse auth = authService.auth(token);
                //2、认证后判断是否成功
                if(auth!=null){
                    // 3、将认证后的参数封装至threadLocal
                    LoginInfo loginInfo = new LoginInfo();
                    loginInfo.setAccount(auth.getPhoneNo());
                    loginInfo.setRegisterId(auth.getRegisterId());
                    LoginUserThreadLocal.setLoginInfo(loginInfo);
                    return true;
                }

                httpServletResponse.setHeader("Content-Type", "application/json");
                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                log.info("无权限访问");
                return false;


            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) throws Exception {

    }
}

