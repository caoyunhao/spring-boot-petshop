package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.CustomWallet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Repository
public interface CustomWalletRepository extends PagingAndSortingRepository<CustomWallet, Long> {

    Optional<CustomWallet> findByCustomId(Long id);
}
