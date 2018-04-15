package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.Custom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
public interface CustomRepository extends PagingAndSortingRepository<Custom, Long> {

    Optional<Custom> findByCustomNameEncryptedAndPassword(String customNameEncrypted, String password);

    int countByCustomName(String customName);

    Page<Custom> findAllByCustomNameLike(String userName, Pageable pageable);
}
