package com.caoyunhao.petshop.common;

import javax.validation.Valid;

/**
 * 基本请求类
 * Created by liang.zhou on 2017/7/13.
 */
public class BaseRequest<T> {
    @Valid
    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
