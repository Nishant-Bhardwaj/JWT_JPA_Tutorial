package com.nishant.jpa.complete.tutorial.repository;

import com.nishant.jpa.complete.tutorial.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {

    @Query(
            value = "select * from user_record where username= :username",
            nativeQuery = true
    )
    UserRecord getByUsername(@Param("username") String username);
}
