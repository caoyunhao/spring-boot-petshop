package com.caoyunhao.petshop.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
public class CommodityImagePK implements Serializable {
    private long commodityId;
    private int imageNumber;

    @Column(name = "commodity_id")
    @Id
    public long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }

    @Column(name = "image_number")
    @Id
    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodityImagePK that = (CommodityImagePK) o;

        if (commodityId != that.commodityId) return false;
        if (imageNumber != that.imageNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (commodityId ^ (commodityId >>> 32));
        result = 31 * result + imageNumber;
        return result;
    }
}
