package com.nishant.jpa.complete.tutorial.repository;

import com.nishant.jpa.complete.tutorial.model.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRequestRepository extends JpaRepository<AuthRequest, String> {

    @Query(value = "select auth from AuthRequest auth where auth.username= :username")
    AuthRequest getByUsername(@Param("username") String username);
}
