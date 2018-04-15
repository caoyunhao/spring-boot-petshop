package com.caoyunhao.petshop.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
@Entity
@Table(name = "purchase_record", schema = "petshop", catalog = "")
public class PurchaseRecord {
    private long id;
    private long customId;
    private long commodityId;
    private String commodityName;
    private String commodityDescription;
    private Timestamp purchaseOrderTime;
    private long purchaseQuantity;
    private double purchaseTotalPrice;
    private String purchaseStatement;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "custom_id")
    public long getCustomId() {
        return customId;
    }

    public void setCustomId(long customId) {
        this.customId = customId;
    }

    @Basic
    @Column(name = "commodity_id")
    public long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }

    @Basic
    @Column(name = "commodity_name")
    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    @Basic
    @Column(name = "commodity_description")
    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    @Basic
    @Column(name = "purchase_order_time")
    public Timestamp getPurchaseOrderTime() {
        return purchaseOrderTime;
    }

    public void setPurchaseOrderTime(Timestamp purchaseOrderTime) {
        this.purchaseOrderTime = purchaseOrderTime;
    }

    @Basic
    @Column(name = "purchase_quantity")
    public long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(long purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    @Basic
    @Column(name = "purchase_total_price")
    public double getPurchaseTotalPrice() {
        return purchaseTotalPrice;
    }

    public void setPurchaseTotalPrice(double purchaseTotalPrice) {
        this.purchaseTotalPrice = purchaseTotalPrice;
    }

    @Basic
    @Column(name = "purchase_statement")
    public String getPurchaseStatement() {
        return purchaseStatement;
    }

    public void setPurchaseStatement(String purchaseStatement) {
        this.purchaseStatement = purchaseStatement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseRecord that = (PurchaseRecord) o;

        if (id != that.id) return false;
        if (customId != that.customId) return false;
        if (commodityId != that.commodityId) return false;
        if (purchaseQuantity != that.purchaseQuantity) return false;
        if (Double.compare(that.purchaseTotalPrice, purchaseTotalPrice) != 0) return false;
        if (commodityName != null ? !commodityName.equals(that.commodityName) : that.commodityName != null)
            return false;
        if (commodityDescription != null ? !commodityDescription.equals(that.commodityDescription) : that.commodityDescription != null)
            return false;
        if (purchaseOrderTime != null ? !purchaseOrderTime.equals(that.purchaseOrderTime) : that.purchaseOrderTime != null)
            return false;
        if (purchaseStatement != null ? !purchaseStatement.equals(that.purchaseStatement) : that.purchaseStatement != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (customId ^ (customId >>> 32));
        result = 31 * result + (int) (commodityId ^ (commodityId >>> 32));
        result = 31 * result + (commodityName != null ? commodityName.hashCode() : 0);
        result = 31 * result + (commodityDescription != null ? commodityDescription.hashCode() : 0);
        result = 31 * result + (purchaseOrderTime != null ? purchaseOrderTime.hashCode() : 0);
        result = 31 * result + (int) (purchaseQuantity ^ (purchaseQuantity >>> 32));
        temp = Double.doubleToLongBits(purchaseTotalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (purchaseStatement != null ? purchaseStatement.hashCode() : 0);
        return result;
    }
}
