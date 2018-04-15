package com.caoyunhao.petshop.common.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {
    /**
     * 将列表转换为另一个列表，由rowConverter实现每行转换
     *
     * @throws Exception
     */
    public static <From, To> List<To> convertList(Iterable<From> fromList, RowConverter<From, To> rowConverter) throws Exception {
        if (fromList == null) return null;
        ArrayList toList = new ArrayList();
        for (From o : fromList) {
            toList.add(rowConverter.convertRow(o));
        }
        return toList;
    }

    /**
     * 将Page<T>转换为另一个Page，由rowConverter实现每行转换
     *
     * @throws Exception
     */
    public static <From, To> Page<To> convertPage(Page<From> fromPage, Pageable pageable, long total, RowConverter<From, To> rowConverter) throws Exception {
        if (fromPage == null) return null;
        PageImpl<To> toPage = new PageImpl<To>(convertList(fromPage.getContent(), rowConverter), pageable, total);
        return toPage;
    }

    /**
     * 返回字符串，若字符串为null返回空
     */
    public static String nullToBlank(String s) {
        if (s == null) return "";
        return s;
    }
}
