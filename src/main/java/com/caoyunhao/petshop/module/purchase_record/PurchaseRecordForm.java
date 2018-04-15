package com.caoyunhao.petshop.module.purchase_record;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class PurchaseRecordForm {
    private long id;
    private long commodityId;
    private String purchaseOrderTime;
    private long purchaseQuantity;
    private double purchaseTotalPrice;
    private String purchaseStatement;
    private String commodityName;
    private String commodityDescription;
    private String commodityImageUrl;

    public long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(long purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public double getPurchaseTotalPrice() {
        return purchaseTotalPrice;
    }

    public void setPurchaseTotalPrice(double purchaseTotalPrice) {
        this.purchaseTotalPrice = purchaseTotalPrice;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Long commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }

    public String getPurchaseOrderTime() {
        return purchaseOrderTime;
    }

    public void setPurchaseOrderTime(String purchaseOrderTime) {
        this.purchaseOrderTime = purchaseOrderTime;
    }

    public String getPurchaseStatement() {
        return purchaseStatement;
    }

    public void setPurchaseStatement(String purchaseStatement) {
        this.purchaseStatement = purchaseStatement;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public String getCommodityImageUrl() {
        return commodityImageUrl;
    }

    public void setCommodityImageUrl(String commodityImageUrl) {
        this.commodityImageUrl = commodityImageUrl;
    }
}