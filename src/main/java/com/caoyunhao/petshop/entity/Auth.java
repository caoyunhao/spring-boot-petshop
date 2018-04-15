package com.caoyunhao.petshop.entity;

import javax.persistence.*;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
@Entity
public class Auth {
    private long id;
    private String authName;
    private String authDescription;

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
    @Column(name = "auth_name")
    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    @Basic
    @Column(name = "auth_description")
    public String getAuthDescription() {
        return authDescription;
    }

    public void setAuthDescription(String authDescription) {
        this.authDescription = authDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auth auth = (Auth) o;

        if (id != auth.id) return false;
        if (authName != null ? !authName.equals(auth.authName) : auth.authName != null) return false;
        if (authDescription != null ? !authDescription.equals(auth.authDescription) : auth.authDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (authName != null ? authName.hashCode() : 0);
        result = 31 * result + (authDescription != null ? authDescription.hashCode() : 0);
        return result;
    }
}
