package com.caoyunhao.petshop.common.security;


import com.caoyunhao.petshop.common.util.HttpUtil;
import com.caoyunhao.petshop.common.util.SignUtil;
import com.caoyunhao.petshop.entity.Custom;
import com.caoyunhao.petshop.entity.CustomNonce;
import com.caoyunhao.petshop.entity.CustomToken;
import com.caoyunhao.petshop.module.login.LoginService;
import com.caoyunhao.petshop.module.custom.CustomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 随机数拦截器
 */
@Component
public class DigestInterceptor extends HandlerInterceptorAdapter {

    final Logger API_LOGGER = LoggerFactory.getLogger("digest");

    @Autowired
    CustomService customService;
    @Autowired
    LoginService loginService;

    /**
     * 在访问接口之前的拦截操作：验证用户的随机数
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 预检请求不触发业务逻辑
        if (request.getMethod().equals(RequestMethod.OPTIONS.toString())) {
            return false;
        }

        try {
            // 获取请求中的验证信息
            RequestAuthorization requestAuthorization = new RequestAuthorization(request);
            // 获取数据库中的随机数
            CustomNonce customNonce = loginService.findCustomNonceByCustomId(requestAuthorization.getCustomId());

            // 过期则写入新随机数
            if(customNonce.getOverdueTime().before(new Date())) {
                customNonce = loginService.updateCustomNonce(requestAuthorization.getCustomId(), request.getRequestURI());
            }
            // 校验随机数和签名
            if (checkNonceAndSignature(customNonce, requestAuthorization)){
                if(customNonce.getCustomNonce().equals(requestAuthorization.getNonce())){
                    request.setAttribute("nonce", requestAuthorization.getNonce());
                    SignUtil.writeAuthorization(response, customNonce.getCustomNonce());
                    return true;
                } else {
                    SignUtil.write401StaleResponse(response, customNonce.getCustomNonce());
                    return false;
                }
            }

        } catch (Exception e) {
            // 校验错误则记录访问者访问的信息
            API_LOGGER.error("authorization error ip:{} target:{} authorization:{}", HttpUtil.getRequestIp(request), request.getServletPath(), request.getHeader("Authorization"));
        }

        // 校验不通过时写入401返回头
        SignUtil.write401Response(response);
        return false;
    }


    /**
     * 在访问接口之后的拦截操作：
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     * 整个请求结束后的操作
     *
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
    throws Exception {

    }

    /**
     * 检验发送的随机数和签名
     */
    private boolean checkNonceAndSignature(CustomNonce customNonce, RequestAuthorization requestAuthorization) throws Exception {
        String sign = requestAuthorization.getSign();
        Long customId = requestAuthorization.getCustomId();

        if (customNonce == null) {
            return false;
        }
        // 判断token
        Custom custom = customService.findCustomById(customId);
        if (custom == null) {
            return false;
        }
        CustomToken customToken = loginService.findCustomTokenByCustomId(customId);
        // token过期？
        if (customToken.getOverdueTime().before(new Date())) {
            return false;
        }
        // 判断sign
        if (!sign.equals(SignUtil.generateSign(customId.toString(), requestAuthorization.getNonce(), customToken.getCustomToken()))) {
            return false;
        }
        return true;
    }
}
