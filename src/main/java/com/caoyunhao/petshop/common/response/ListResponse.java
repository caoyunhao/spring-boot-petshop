package com.caoyunhao.petshop.common.response;

import com.caoyunhao.petshop.common.util.ConvertUtil;
import com.caoyunhao.petshop.common.util.RowConverter;
import org.springframework.beans.BeanUtils;

import java.util.List;


/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public class ListResponse<T> extends BaseResponse<List<T>> {
    public <From> ListResponse(List<From> dataList, RowConverter<From, T> rowConverter) throws Exception {
        data = ConvertUtil.convertList(dataList, rowConverter);
    }

    public ListResponse() throws Exception {

    }

    public static <From, T> ListResponse<T> copyListProperties(List<From> dataList, final Class<T> clazz) throws Exception {
        ListResponse listResponse = new ListResponse();
        listResponse.data = ConvertUtil.convertList(dataList, new RowConverter<From, T>() {
            @Override
            public T convertRow(From from) throws Exception {
                T t = (T) (clazz.newInstance());
                BeanUtils.copyProperties(from, t);
                return t;
            }
        });
        return listResponse;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
