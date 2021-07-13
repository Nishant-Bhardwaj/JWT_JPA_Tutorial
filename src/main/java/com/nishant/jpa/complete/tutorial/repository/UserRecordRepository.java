package com.nishant.jpa.complete.tutorial.repository;

import com.nishant.jpa.complete.tutorial.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {
}
