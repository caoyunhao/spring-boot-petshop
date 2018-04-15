package com.caoyunhao.petshop.module.login;

import com.caoyunhao.petshop.common.BaseRequest;
import com.caoyunhao.petshop.common.response.BaseResponse;
import com.caoyunhao.petshop.entity.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@RequestMapping(value = "/login")
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(method = RequestMethod.POST)
    public BaseResponse<LoginData> login(@RequestBody @Valid BaseRequest<LoginForm> request, HttpSession httpSession, HttpServletResponse httpServletResponse, HttpServletRequest httpRequest) throws Exception {
        return new BaseResponse<>(loginService.login(request.getData(), httpSession, httpServletResponse, httpRequest));
    }

    @RequestMapping(value = "/refreshNonce", method = RequestMethod.GET)
    public BaseResponse<LoginData> refreshNonce(@RequestAttribute("custom") Custom custom, HttpSession httpSession, HttpServletResponse httpServletResponse, HttpServletRequest httpRequest) throws Exception {
        loginService.refreshNonce(custom.getId(), httpSession, httpServletResponse, httpRequest);
        return new BaseResponse<>();
    }
}
