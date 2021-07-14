package com.nishant.jpa.complete.tutorial.service.impl;

import com.nishant.jpa.complete.tutorial.model.AuthRequest;
import com.nishant.jpa.complete.tutorial.repository.AuthRequestRepository;
import com.nishant.jpa.complete.tutorial.service.AuthRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthRequestServiceImpl implements AuthRequestService {

    @Autowired
    AuthRequestRepository authRequestRepository;

    @Override
    public AuthRequest getUserRecordByUsername(String username) {
        return authRequestRepository.getByUsername(username);
    }
}
