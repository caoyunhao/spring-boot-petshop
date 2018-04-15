package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.CommodityCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public interface CommodityCategoryRepository extends PagingAndSortingRepository<CommodityCategory, Long> {

    List<CommodityCategory> findAllByCommodityId(Long commodityId);

    List<CommodityCategory> findAllByCategoryId(Long categoryId);

    void deleteByCommodityId(Long commodityId);
}
