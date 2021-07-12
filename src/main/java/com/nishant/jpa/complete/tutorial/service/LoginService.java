package com.nishant.jpa.complete.tutorial.service;

import com.nishant.jpa.complete.tutorial.model.AuthRequest;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    String generateToken(AuthRequest authRequest);
}
