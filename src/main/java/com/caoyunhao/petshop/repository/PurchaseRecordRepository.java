package com.caoyunhao.petshop.repository;

import com.caoyunhao.petshop.entity.PurchaseRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author Yunhao.Cao
 * @version 1.0 2018/4/8
 */
@Repository
public interface PurchaseRecordRepository extends PagingAndSortingRepository<PurchaseRecord, Long> {

    Page<PurchaseRecord> findByPurchaseOrderTimeBetweenAndCustomId(Timestamp startTime, Timestamp endTime, Pageable pageable, Long customId);

    Page<PurchaseRecord> findByPurchaseOrderTimeBeforeAndCustomId(Timestamp endTime, Pageable pageable, Long customId);

    Page<PurchaseRecord> findByPurchaseOrderTimeAfterAndCustomId(Timestamp startTime, Pageable pageable, Long customId);

    Page<PurchaseRecord> findByCustomId(Long customId, Pageable pageable);

    Optional<PurchaseRecord> findByCustomIdAndId(Long customId, Long id);
}
