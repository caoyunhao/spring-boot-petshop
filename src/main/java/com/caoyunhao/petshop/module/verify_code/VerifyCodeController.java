package com.caoyunhao.petshop.module.verify_code;

import com.caoyunhao.petshop.common.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/4
 */
@RequestMapping(value = "/verifycode")
@RestController
public class VerifyCodeController {

    @Autowired
    VerifyCodeService verifyCodeService;

    @RequestMapping(method = RequestMethod.GET)
    public BaseResponse<VerifyCodeResponseData> requestVerifyCode() throws Exception {
        return new BaseResponse<>(verifyCodeService.requestVerifyCode());
    }
}
