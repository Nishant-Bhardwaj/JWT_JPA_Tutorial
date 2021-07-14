package com.nishant.jpa.complete.tutorial.service;

import com.nishant.jpa.complete.tutorial.model.AuthRequest;
import com.nishant.jpa.complete.tutorial.model.UserRecord;
import org.springframework.stereotype.Service;

@Service
public interface AuthRequestService {

    AuthRequest getUserRecordByUsername(String username);
}
