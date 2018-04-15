package com.caoyunhao.petshop.entity;

import javax.persistence.*;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
@Entity
public class Commodity {
    private long id;
    private String commodityName;
    private String commodityDescription;
    private double commodityPrice;

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
    @Column(name = "commodity_price")
    public double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commodity commodity = (Commodity) o;

        if (id != commodity.id) return false;
        if (Double.compare(commodity.commodityPrice, commodityPrice) != 0) return false;
        if (commodityName != null ? !commodityName.equals(commodity.commodityName) : commodity.commodityName != null)
            return false;
        if (commodityDescription != null ? !commodityDescription.equals(commodity.commodityDescription) : commodity.commodityDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (commodityName != null ? commodityName.hashCode() : 0);
        result = 31 * result + (commodityDescription != null ? commodityDescription.hashCode() : 0);
        temp = Double.doubleToLongBits(commodityPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
