package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.VerifyRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Repository
public interface VerifyRecordRepository extends PagingAndSortingRepository<VerifyRecord, Long> {

    Optional<VerifyRecord> findByVerifyCodeAndVerifyToken(String verifyCode, String verifyToken);
}
