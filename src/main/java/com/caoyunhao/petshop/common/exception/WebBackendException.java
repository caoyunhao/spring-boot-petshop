package com.caoyunhao.petshop.common.exception;


import com.caoyunhao.petshop.common.util.CommonUtil;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class WebBackendException extends Exception {
    private ErrorCode error;
    private int errorCode;
    private String errorInfo;

    /**
     * 若定义了错误码则匹配找出相应的错误说明
     */
    public WebBackendException(ErrorCode e) {
        this.error = e;
        this.errorCode = e.getErrorCode();
        this.errorInfo = e.getErrorInfo();
    }

    /**
     * 直接写错误说明
     */
    public WebBackendException(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * 同时写错误码和错误说明
     */
    public WebBackendException(int errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public ErrorCode getError() {
        return this.error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return errorCode + ":" + CommonUtil.NullToBlank(errorInfo);
    }
}
