package com.caoyunhao.petshop.module.custom;

import com.caoyunhao.petshop.common.util.RegexValidatorUtil;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import java.util.List;

public class CustomForm {
    @Pattern(regexp = RegexValidatorUtil.REGEX_NICKNAME)
    private String customNickname;
    @Pattern(regexp = RegexValidatorUtil.REGEX_MOBILE)
    private String customPhoneNumber;
    @Email
    private String customEmail;

    private List<Long> roleIdList;

    public String getCustomNickname() {
        return customNickname;
    }

    public void setCustomNickname(String customNickname) {
        this.customNickname = customNickname;
    }

    public String getCustomPhoneNumber() {
        return customPhoneNumber;
    }

    public void setCustomPhoneNumber(String customPhoneNumber) {
        this.customPhoneNumber = customPhoneNumber;
    }

    public String getCustomEmail() {
        return customEmail;
    }

    public void setCustomEmail(String customEmail) {
        this.customEmail = customEmail;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public interface Create {
    }

    public interface Update {
    }
}
