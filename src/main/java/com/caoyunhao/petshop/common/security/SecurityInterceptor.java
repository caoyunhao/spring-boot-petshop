package com.caoyunhao.petshop.common.security;


import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;
import com.caoyunhao.petshop.entity.Custom;
import com.caoyunhao.petshop.entity.CustomToken;
import com.caoyunhao.petshop.module.custom.CustomService;
import com.caoyunhao.petshop.module.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 全局拦截器，检查用户是否有权限访问页面
 */
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    CustomService customService;
    @Autowired
    LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getServletPath().replace(".html", "");
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        // 记录访问时间
        if (LOG.isDebugEnabled()) {
            long startTime = System.currentTimeMillis();
            request.setAttribute("requestStartTime", startTime);
        }

        // 若为跨域预请求，不检查权限
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        RequestAuthorization requestAuthorization = new RequestAuthorization(request);

        CustomToken customToken = loginService.findCustomTokenByCustomId(requestAuthorization.getCustomId());
        //判断是用户是否存在且未过期,否则返回错误
        if (customToken == null || customToken.getOverdueTime().before(new Date())) {
            LOG.debug("[interceptor exception], sessionId:{} token overdue", sessionId);
            throw new WebBackendException(ErrorCode.TOKEN_ERROR);
        } else {
            //未过期则刷新过期时间
            loginService.updateTokenOverdue(customToken);
        }

        //将用户放入request
        Custom custom = customService.findCustomById(customToken.getCustomId());
        request.setAttribute("custom", custom);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /* 收集一次用户访问的log信息  */
        if (LOG.isDebugEnabled()) {
            // 计算整个请求花费的时间
            long startTime = (Long) request.getAttribute("requestStartTime");
            long endTime = System.currentTimeMillis();
            long executeTime = endTime - startTime;

            String sessionId = request.getSession().getId();
            LOG.debug("[response info], sessionId:{}, status:{}, handler:{}, executeTime:{}ms",
                    sessionId, response.getStatus(), handler, executeTime);
        }
    }

}
