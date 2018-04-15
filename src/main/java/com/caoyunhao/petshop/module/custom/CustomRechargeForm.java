package com.caoyunhao.petshop.module.custom;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class CustomRechargeForm {
    @Max(value = 10000)
    @Min(value = 0)
    private Double rechargeNumber;

    public Double getRechargeNumber() {
        return rechargeNumber;
    }

    public void setRechargeNumber(Double rechargeNumber) {
        this.rechargeNumber = rechargeNumber;
    }
}
