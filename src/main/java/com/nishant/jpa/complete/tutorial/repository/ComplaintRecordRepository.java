package com.nishant.jpa.complete.tutorial.repository;

import com.nishant.jpa.complete.tutorial.model.ComplaintRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRecordRepository extends JpaRepository<ComplaintRecord, Long> {
}
