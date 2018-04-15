package com.caoyunhao.petshop.common.util;


/**
 * @param <From>输入类
 * @param <To>输出类
 * @author liang.zhou
 * 将list中的每条记录转换为另一种记录
 */
public interface RowConverter<From, To> {
    public To convertRow(From from) throws Exception;
}
