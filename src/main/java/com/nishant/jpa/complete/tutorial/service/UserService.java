package com.nishant.jpa.complete.tutorial.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nishant.jpa.complete.tutorial.model.UserRecord;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserRecord getUserRecordByUsername(String username);

    UserRecord createUser(JsonNode userDetailNode);
}
