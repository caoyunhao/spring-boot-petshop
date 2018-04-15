package com.caoyunhao.petshop.module.login;

import javax.validation.constraints.NotNull;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class LoginForm {
    @NotNull
    private String customName;
    @NotNull
    private String password;
    @NotNull
    private String verifyCode;
    @NotNull
    private String verifyToken;

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
    }
}
