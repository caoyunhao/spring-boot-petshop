package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.CustomRole;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jie.yao
 * @date 2017/9/26
 */
@Repository
public interface CustomRoleRepository extends PagingAndSortingRepository<CustomRole, Long> {

    List<CustomRole> findByCustomId(Long userId);
}
