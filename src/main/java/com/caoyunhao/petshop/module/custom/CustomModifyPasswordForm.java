package com.caoyunhao.petshop.module.custom;

import com.caoyunhao.petshop.common.util.RegexValidatorUtil;

import javax.validation.constraints.Pattern;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class CustomModifyPasswordForm {
    String oldPassword;
    @Pattern(regexp = RegexValidatorUtil.REGEX_PASSWORD)
    String password;
    String repeatedPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }

    public boolean isEqual() {
        return password.equals(repeatedPassword);
    }
}
