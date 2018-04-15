package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.CustomNonce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public interface CustomNonceRepository extends PagingAndSortingRepository<CustomNonce, Long> {

    Optional<CustomNonce> findByCustomId(Long id);

    void deleteByCustomId(Long id);
}
