package com.caoyunhao.petshop.module.logout;

import com.caoyunhao.petshop.common.response.BaseResponse;
import com.caoyunhao.petshop.entity.Custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@RequestMapping("/logout")
@RestController
public class LogoutController {

    @Autowired
    LogoutService logoutService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponse logout(@RequestAttribute("custom") Custom custom) throws Exception {
        logoutService.logout(custom.getId());
        return new BaseResponse<>();
    }
}
