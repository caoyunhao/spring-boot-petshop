package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.ImageStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public interface ImageStoreRepository extends PagingAndSortingRepository<ImageStore, Long> {

    Optional<ImageStore> findByHash(String hash);
}
