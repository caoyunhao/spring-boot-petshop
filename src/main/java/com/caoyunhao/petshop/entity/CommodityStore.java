package com.caoyunhao.petshop.entity;

import javax.persistence.*;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
@Entity
@Table(name = "commodity_store", schema = "petshop", catalog = "")
public class CommodityStore {
    private long commodityId;
    private long commoditySold;
    private long commodityLast;

    @Id
    @Column(name = "commodity_id")
    public long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }

    @Basic
    @Column(name = "commodity_sold")
    public long getCommoditySold() {
        return commoditySold;
    }

    public void setCommoditySold(long commoditySold) {
        this.commoditySold = commoditySold;
    }

    @Basic
    @Column(name = "commodity_last")
    public long getCommodityLast() {
        return commodityLast;
    }

    public void setCommodityLast(long commodityLast) {
        this.commodityLast = commodityLast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodityStore that = (CommodityStore) o;

        if (commodityId != that.commodityId) return false;
        if (commoditySold != that.commoditySold) return false;
        if (commodityLast != that.commodityLast) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (commodityId ^ (commodityId >>> 32));
        result = 31 * result + (int) (commoditySold ^ (commoditySold >>> 32));
        result = 31 * result + (int) (commodityLast ^ (commodityLast >>> 32));
        return result;
    }
}
