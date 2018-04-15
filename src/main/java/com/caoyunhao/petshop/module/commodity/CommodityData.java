package com.caoyunhao.petshop.module.commodity;

import com.caoyunhao.petshop.entity.Category;

import java.util.List;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class CommodityData {
    private long id;
    private String commodityName;
    private double commodityPrice;
    private String commodityDescription;
    private long commodityLast;
    private long commoditySold;
    private List<Category> commodityCategoryList;
    private String commodityImageUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public long getCommodityLast() {
        return commodityLast;
    }

    public void setCommodityLast(long commodityLast) {
        this.commodityLast = commodityLast;
    }

    public long getCommoditySold() {
        return commoditySold;
    }

    public void setCommoditySold(long commoditySold) {
        this.commoditySold = commoditySold;
    }

    public List<Category> getCommodityCategoryList() {
        return commodityCategoryList;
    }

    public void setCommodityCategoryList(List<Category> commodityCategoryList) {
        this.commodityCategoryList = commodityCategoryList;
    }

    public String getCommodityImageUrl() {
        return commodityImageUrl;
    }

    public void setCommodityImageUrl(String commodityImageUrl) {
        this.commodityImageUrl = commodityImageUrl;
    }
}
