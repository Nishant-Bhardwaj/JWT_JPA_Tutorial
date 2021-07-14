package com.nishant.jpa.complete.tutorial.service;

import com.nishant.jpa.complete.tutorial.model.UserRecord;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserRecord getUserRecordByUsername(String username);
}
