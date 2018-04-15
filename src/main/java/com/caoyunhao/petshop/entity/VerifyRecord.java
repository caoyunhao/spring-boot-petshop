package com.caoyunhao.petshop.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
@Entity
@Table(name = "verify_record", schema = "petshop", catalog = "")
public class VerifyRecord {
    private long id;
    private String verifyToken;
    private String verifyCode;
    private Timestamp overdueTime;

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
    @Column(name = "verify_token")
    public String getVerifyToken() {
        return verifyToken;
    }

    public void setVerifyToken(String verifyToken) {
        this.verifyToken = verifyToken;
    }

    @Basic
    @Column(name = "verify_code")
    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
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

        VerifyRecord that = (VerifyRecord) o;

        if (id != that.id) return false;
        if (verifyToken != null ? !verifyToken.equals(that.verifyToken) : that.verifyToken != null) return false;
        if (verifyCode != null ? !verifyCode.equals(that.verifyCode) : that.verifyCode != null) return false;
        if (overdueTime != null ? !overdueTime.equals(that.overdueTime) : that.overdueTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (verifyToken != null ? verifyToken.hashCode() : 0);
        result = 31 * result + (verifyCode != null ? verifyCode.hashCode() : 0);
        result = 31 * result + (overdueTime != null ? overdueTime.hashCode() : 0);
        return result;
    }
}
