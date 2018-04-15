package com.caoyunhao.petshop.module.verify_code;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/4
 */
public class VerifyCodeResponseData {
    private String imageData;
    private String token;

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
