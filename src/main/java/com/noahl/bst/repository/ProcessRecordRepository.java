package com.noahl.bst.repository;

import com.noahl.bst.model.ProcessRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRecordRepository extends JpaRepository<ProcessRecord, Long> {
    Page<ProcessRecord> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
