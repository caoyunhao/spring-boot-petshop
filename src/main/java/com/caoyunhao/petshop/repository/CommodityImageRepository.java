package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.CommodityImage;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public interface CommodityImageRepository extends PagingAndSortingRepository<CommodityImage, Long> {
    Optional<CommodityImage> findByCommodityId(Long commodityId);

    void deleteAllByCommodityId(Long commodityId);
}
