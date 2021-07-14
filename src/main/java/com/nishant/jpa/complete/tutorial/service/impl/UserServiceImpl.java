package com.nishant.jpa.complete.tutorial.service.impl;

import com.nishant.jpa.complete.tutorial.model.UserRecord;
import com.nishant.jpa.complete.tutorial.repository.UserRecordRepository;
import com.nishant.jpa.complete.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRecordRepository userRecordRepository;

    @Override
    public UserRecord getUserRecordByUsername(String username) {
        return userRecordRepository.getByUsername(username);
    }
}
