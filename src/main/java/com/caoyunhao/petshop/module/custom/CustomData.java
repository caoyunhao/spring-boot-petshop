package com.caoyunhao.petshop.module.custom;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class CustomData {
    private String customName;
    private String customNickname;
    private String customPhoneNumber;
    private String customEmail;
    private Double walletBalance;
    private Long customImageId;
    private String customImageUrl;

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

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

    public Double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(Double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public Long getCustomImageId() {
        return customImageId;
    }

    public void setCustomImageId(Long customImageId) {
        this.customImageId = customImageId;
    }

    public String getCustomImageUrl() {
        return customImageUrl;
    }

    public void setCustomImageUrl(String customImageUrl) {
        this.customImageUrl = customImageUrl;
    }
}
