package com.caoyunhao.petshop.module.commodity;

import com.caoyunhao.petshop.common.page.PageParams;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class CommoditySearchParams extends PageParams {
    private String q;

    private Long categoryId;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
