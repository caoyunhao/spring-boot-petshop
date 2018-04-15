package com.caoyunhao.petshop.module.verify_code;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/4
 */
public class VerifyCode {
    private String imageData;
    private String token;
    private String text;
    private boolean changed;
    private boolean valid;

    public VerifyCode() {
        changed = false;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        changed = true;
        this.imageData = imageData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        changed = true;
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        changed = true;
        this.text = text;
    }


    public boolean isValid() {
        if (changed) {
            valid = !("".equals(imageData) || "".equals(text) || "".equals(token));
            changed = false;
        }
        return valid;
    }
}
