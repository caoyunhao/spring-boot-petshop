package com.caoyunhao.petshop.module.custom;

import org.hibernate.validator.constraints.NotBlank;

public class CustomFormWithPassword extends CustomForm {

    @NotBlank(message = "{password.blank}", groups = Create.class)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
