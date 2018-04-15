package com.caoyunhao.petshop.common;

import com.caoyunhao.petshop.common.response.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public BaseResponse index() {
        return new BaseResponse();
    }
}
