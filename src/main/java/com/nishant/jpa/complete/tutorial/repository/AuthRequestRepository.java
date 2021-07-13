package com.nishant.jpa.complete.tutorial.repository;

import com.nishant.jpa.complete.tutorial.model.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRequestRepository extends JpaRepository<AuthRequest, String> {
}
