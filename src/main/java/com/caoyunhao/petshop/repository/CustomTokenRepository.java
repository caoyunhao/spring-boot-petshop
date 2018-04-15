package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.CustomToken;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Repository
public interface CustomTokenRepository extends PagingAndSortingRepository<CustomToken, Long> {

    Optional<CustomToken> findByCustomId(Long id);

    void deleteByCustomId(Long id);
}
