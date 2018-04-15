package com.caoyunhao.petshop.common.util;

import com.caoyunhao.petshop.common.exception.ErrorCode;
import com.caoyunhao.petshop.common.exception.WebBackendException;

import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
public class DataUtil {
    public static <T> T getOrElse(IGetDataMethod<T> get, Long id)
            throws WebBackendException {
        return getOrElse(get, id, true, null);
    }

    public static <T> T getOrElse(IGetDataMethod<T> get, Long id, boolean throw_)
            throws WebBackendException {
        return getOrElse(get, id, throw_, null);
    }

    public static <T> T getOrElse(IGetDataMethod<T> get, Long id, boolean throw_, T default_)
            throws WebBackendException {
        Optional<T> optional = get.get(id);
        if (optional.isPresent())
            return optional.get();
        else if (throw_)
            throw new WebBackendException(ErrorCode.QUERY_DATA_EMPTY);
        else
            return default_;
    }
}
