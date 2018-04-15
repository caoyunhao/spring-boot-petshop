package com.caoyunhao.petshop.common.util;


import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class CommonUtil {

    /**
     * 处理参数校验错误
     *
     * @param bindingResult 校验信息
     * @throws WebBackendException
     */
    public static void handleValidation(BindingResult bindingResult)
            throws WebBackendException {
        if (bindingResult.hasErrors()) {
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list) {
                throw new WebBackendException(ErrorCode.NOT_VALID_PARAM);
            }
        }
    }

    /**
     * null转空字符串
     */
    public static String NullToBlank(Object str) {
        return str == null ? "" : str.toString();
    }

    /**
     * 转换数字至Int,错误转为null
     */
    public static Integer parseIntegerDefaultNull(Object b) {
        if (b == null) {
            return null;
        }
        try {
            return Integer.parseInt(b.toString());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转换数字至Int,错误转为0
     */
    public static Integer parseIntegerDefaultZero(Object b) {
        if (b == null) {
            return 0;
        }
        try {
            return Integer.parseInt(b.toString());
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 获得请求的IP
     */
    public static String getRemoteIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}
