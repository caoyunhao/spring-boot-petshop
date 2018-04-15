package com.caoyunhao.petshop.module.purchase_record;

import com.caoyunhao.petshop.common.page.PageParams;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class DateForm extends PageParams{

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    private String start;
    private String end;
}
