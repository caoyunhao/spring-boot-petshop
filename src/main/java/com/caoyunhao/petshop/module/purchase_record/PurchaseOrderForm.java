package com.caoyunhao.petshop.module.purchase_record;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class PurchaseOrderForm {
    private long commodityId;
    private int purchaseQuantity;

    public long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }
}
