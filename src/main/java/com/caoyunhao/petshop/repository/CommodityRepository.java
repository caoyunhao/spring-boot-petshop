package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Repository
public interface CommodityRepository extends PagingAndSortingRepository<Commodity, Long> {

    Page<Commodity> findAllByCommodityNameLike(String name, Pageable pageable);

    List<Commodity> findAllByCommodityNameLike(String name);
}
