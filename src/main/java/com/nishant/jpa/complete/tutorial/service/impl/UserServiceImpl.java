package com.nishant.jpa.complete.tutorial.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.nishant.jpa.complete.tutorial.model.AuthRequest;
import com.nishant.jpa.complete.tutorial.model.Department;
import com.nishant.jpa.complete.tutorial.model.UserRecord;
import com.nishant.jpa.complete.tutorial.repository.UserRecordRepository;
import com.nishant.jpa.complete.tutorial.service.AuthRequestService;
import com.nishant.jpa.complete.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRecordRepository userRecordRepository;

    @Autowired
    AuthRequestService authRequestService;

    @Override
    public UserRecord getUserRecordByUsername(String username) {
        return userRecordRepository.getByUsername(username);
    }

    @Transactional
    @Override
    public UserRecord createUser(JsonNode userDetailNode) {

        AuthRequest authRequest = authRequestService.getUserRecordByUsername(userDetailNode.get("username").asText());

        if(authRequest==null){
            authRequest = AuthRequest.builder()
                .username(userDetailNode.get("username").asText())
                .password(userDetailNode.get("password").asText())
                .build();

            authRequestService.createAuthUserRecord(authRequest);
        }

        Department department = Department.builder()
                .departmentName(userDetailNode.get("department").asText())
                .build();

        UserRecord userRecord = UserRecord.builder()
                .authRequestUsername(authRequest)
                .userDepartment(department)
                .userEmail(userDetailNode.get("email").asText())
                .mobile(userDetailNode.get("mobile").asLong())
                .build();

        userRecordRepository.saveAndFlush(userRecord);

        return userRecord;
    }
}
