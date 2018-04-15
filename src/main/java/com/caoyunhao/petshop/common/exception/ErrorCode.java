package com.caoyunhao.petshop.common.exception;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public enum ErrorCode {
    // 以下为系统错误
    SUCCESS(0, "成功"),
    ERROR_404(404, "您访问的页面不存在"),
    NOT_VALID_PARAM(10001, "参数格式不正确"),
    //    SERVICE_WRONG(10002,"服务层调用错误"),
    //登录相关
    USER_PASSWORD_NOT_VALID(10103, "用户名密码不正确"),
    TOKEN_ERROR(10104, "token已过期"),
    VERIFY_CODE_NOT_VALID(10201, "验证码无效，请刷新重试"),
    VERIFY_CODE_GET_FAILURE(10202, "获取验证码失败，请刷新重试"),
    VERIFY_CODE_NEED_TOKEN(10301, "获取验证码需要Token"),
    LOGOUT_FAILED(70101, "退出登录失败"),

    //用户相关
    CUSTOM_NOT_FOUND(20101, "找不到用户"),
    CUSTOM_EXISTS(20102, "用户名已存在"),

    QUERY_DATA_EMPTY(31002, "数据查询为空"),
    TWO_PASSWORDS_NOT_EQUAL(33001, "两次输入的密码不相等"),
    MODIFY_PASSWORDS_FAILURE(33002, "密码修改失败"),

    DELETE_USED_DATA(41002, "不能删除被使用的数据"),

    //文件相关
    FILE_PARAM_IS_REQUIRED(51001, "文件参数是必需的"),
    FILE_NOT_VALID(51011, "文件无效"),
    IMAGE_ID_NOT_VALID(53101, "图片Id无效"),

    AUTH_EXISTS(40001, "权限已经存在"),
    AUTH_NOT_FOUND(40002, "权限不存在"),

    //角色相关
    ROLE_EXISTS(40101, "角色已存在"),
    ROLE_NOT_FOUND(40102, "找不到角色"),

    //品类相关
    CATEGORY_EXISTS(50101, "品类已存在"),
    CATEGORY_NOT_FOUND(50102, "找不到品类"),
    CATEGORY_HAVE_NO_COMMODITY(50102, "该品类没有商品"),

    //商品相关
    COMMODITY_EXISTS(60101, "商品已存在"),
    COMMODITY_NOT_FOUND(60102, "找不到商品"),

    //购买相关
    BALANCE_NOT_ENOUGH(70001, "余额不足"),

    //日期相关
    TIME_REQUIRED(80001, "日期不正确"),

    //购买记录相关
    PURCHASERECORD_NOT_FOUND(90001, "找不到购买记录"),
    PAY_FAILED(90002, "支付失败"),
    COMMODITY_STORE_NOT_ENOUGH(90003, "库存不足"),
    PURCHASEQUANTITY_NOT_VALID(90004, "购买数量不正确"),

    OTHER(99991, "其他类型错误");
    private int errorCode;
    private String errorInfo;

    ErrorCode(int errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
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

}
