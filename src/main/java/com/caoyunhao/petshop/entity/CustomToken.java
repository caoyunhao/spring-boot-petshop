package com.caoyunhao.petshop.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
@Entity
@Table(name = "custom_token", schema = "petshop", catalog = "")
public class CustomToken {
    private long customId;
    private String customToken;
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
    @Column(name = "custom_token")
    public String getCustomToken() {
        return customToken;
    }

    public void setCustomToken(String customToken) {
        this.customToken = customToken;
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

        CustomToken that = (CustomToken) o;

        if (customId != that.customId) return false;
        if (customToken != null ? !customToken.equals(that.customToken) : that.customToken != null) return false;
        if (overdueTime != null ? !overdueTime.equals(that.overdueTime) : that.overdueTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (customId ^ (customId >>> 32));
        result = 31 * result + (customToken != null ? customToken.hashCode() : 0);
        result = 31 * result + (overdueTime != null ? overdueTime.hashCode() : 0);
        return result;
    }
}
