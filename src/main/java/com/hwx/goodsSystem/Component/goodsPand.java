package com.hwx.goodsSystem.Component;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hwx.goodsSystem.util.goodsJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class goodsPand implements HandlerInterceptor {

    /**
     * 导入JWT通用包
     */
    @Autowired
    private goodsJWT goodsJWT;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");

        DecodedJWT validate = goodsJWT.validate(token);


        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
