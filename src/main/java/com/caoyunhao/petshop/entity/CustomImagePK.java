package com.caoyunhao.petshop.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
public class CustomImagePK implements Serializable {
    private long customId;
    private long imageNumber;

    @Column(name = "custom_id")
    @Id
    public long getCustomId() {
        return customId;
    }

    public void setCustomId(long customId) {
        this.customId = customId;
    }

    @Column(name = "image_number")
    @Id
    public long getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(long imageNumber) {
        this.imageNumber = imageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomImagePK that = (CustomImagePK) o;

        if (customId != that.customId) return false;
        if (imageNumber != that.imageNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (customId ^ (customId >>> 32));
        result = 31 * result + (int) (imageNumber ^ (imageNumber >>> 32));
        return result;
    }
}
