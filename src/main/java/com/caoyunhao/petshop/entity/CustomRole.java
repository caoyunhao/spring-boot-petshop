package com.caoyunhao.petshop.entity;

import javax.persistence.*;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
@Entity
@Table(name = "custom_role", schema = "petshop", catalog = "")
@IdClass(CustomRolePK.class)
public class CustomRole {
    private long customId;
    private long roleId;

    @Id
    @Column(name = "custom_id")
    public long getCustomId() {
        return customId;
    }

    public void setCustomId(long customId) {
        this.customId = customId;
    }

    @Id
    @Column(name = "role_id")
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomRole that = (CustomRole) o;

        if (customId != that.customId) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (customId ^ (customId >>> 32));
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        return result;
    }
}
