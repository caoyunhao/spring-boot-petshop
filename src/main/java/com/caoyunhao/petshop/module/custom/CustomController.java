package com.caoyunhao.petshop.module.custom;

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
@RestController
@RequestMapping("/customs")
public class CustomController {

    @Autowired
    CustomService customService;

    /**
     * 获取用户
     */
    @RequestMapping(method = RequestMethod.GET)
    public BaseResponse<CustomData> findCustom(@RequestAttribute("custom") Custom custom) throws Exception {
        return new BaseResponse<>(customService.findCustomDataById(custom.getId()));
    }

//    /**
//     * 增加用户
//     */
//    @RequestMapping(method = RequestMethod.POST)
//    public BaseResponse<CustomData> addCustom(@RequestBody BaseRequest<CustomFormWithPassword> request) throws Exception {
//        return new BaseResponse<>(customService.addCustom(request.getData()));
//    }

    /**
     * 修改用户
     */
    @RequestMapping(method = RequestMethod.PUT)
    public BaseResponse<CustomData> updateCustom(@RequestBody @Valid BaseRequest<CustomForm> request, @RequestAttribute("custom") Custom custom) throws Exception {
        return new BaseResponse<>(customService.updateCustom(request.getData(), custom.getId()));
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/modify-password", method = RequestMethod.PUT)
    public BaseResponse<CustomModifyPasswordData> modifyCustomPassword(@RequestBody BaseRequest<CustomModifyPasswordForm> request, @RequestAttribute("custom") Custom custom, HttpSession httpSession, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return new BaseResponse<>(customService.modifyCustomPassword(request.getData(), custom.getId(), httpSession, httpServletRequest, httpServletResponse));
    }

    /**
     * 充值
     */
    @RequestMapping(value = "/recharge", method = RequestMethod.PUT)
    public BaseResponse recharge(@RequestBody @Valid BaseRequest<CustomRechargeForm> request, @RequestAttribute("custom") Custom custom) throws Exception {
        customService.recharge(custom.getId(), request.getData());
        return new BaseResponse();
    }
}
