package com.caoyunhao.petshop.common.util;

import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/9
 */
public interface IGetDataMethod<T> {
    Optional<T> get(Long id);
}
