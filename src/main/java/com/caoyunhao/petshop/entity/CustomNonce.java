package com.caoyunhao.petshop.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
@Entity
@Table(name = "custom_nonce", schema = "petshop", catalog = "")
public class CustomNonce {
    private long customId;
    private String customNonce;
    private Timestamp overdueTime;

    @Id
    @Column(name = "custom_id")
    public long getCustomId() {
        return customId;
    }

    public void setCustomId(long customId) {
        this.customId = customId;
    }

    @Basic
    @Column(name = "custom_nonce")
    public String getCustomNonce() {
        return customNonce;
    }

    public void setCustomNonce(String customNonce) {
        this.customNonce = customNonce;
    }

    @Basic
    @Column(name = "overdue_time")
    public Timestamp getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Timestamp overdueTime) {
        this.overdueTime = overdueTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomNonce that = (CustomNonce) o;

        if (customId != that.customId) return false;
        if (customNonce != null ? !customNonce.equals(that.customNonce) : that.customNonce != null) return false;
        if (overdueTime != null ? !overdueTime.equals(that.overdueTime) : that.overdueTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (customId ^ (customId >>> 32));
        result = 31 * result + (customNonce != null ? customNonce.hashCode() : 0);
        result = 31 * result + (overdueTime != null ? overdueTime.hashCode() : 0);
        return result;
    }
}
