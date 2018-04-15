package com.caoyunhao.petshop.entity;

import javax.persistence.*;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
@Entity
@Table(name = "custom_wallet", schema = "petshop", catalog = "")
public class CustomWallet {
    private long id;
    private long customId;
    private double walletBalance;

    @Id
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
    @Column(name = "wallet_balance")
    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomWallet that = (CustomWallet) o;

        if (id != that.id) return false;
        if (customId != that.customId) return false;
        if (Double.compare(that.walletBalance, walletBalance) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (customId ^ (customId >>> 32));
        temp = Double.doubleToLongBits(walletBalance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
