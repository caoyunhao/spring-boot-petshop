package com.caoyunhao.petshop.entity;

import javax.persistence.*;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
@Entity
@Table(name = "custom_image", schema = "petshop", catalog = "")
@IdClass(CustomImagePK.class)
public class CustomImage {
    private long customId;
    private long imageId;
    private long imageNumber;

    @Id
    @Column(name = "custom_id")
    public long getCustomId() {
        return customId;
    }

    public void setCustomId(long customId) {
        this.customId = customId;
    }

    @Basic
    @Column(name = "image_id")
    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    @Id
    @Column(name = "image_number")
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

        CustomImage that = (CustomImage) o;

        if (customId != that.customId) return false;
        if (imageId != that.imageId) return false;
        if (imageNumber != that.imageNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (customId ^ (customId >>> 32));
        result = 31 * result + (int) (imageId ^ (imageId >>> 32));
        result = 31 * result + (int) (imageNumber ^ (imageNumber >>> 32));
        return result;
    }
}
