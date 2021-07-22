package com.nishant.jpa.complete.tutorial.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.nishant.jpa.complete.tutorial.model.UserRecord;
import com.nishant.jpa.complete.tutorial.service.UserService;
import com.nishant.jpa.complete.tutorial.util.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserManagementController {

    Logger logger = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    UserService userService;

    @Autowired
    MapperUtil mapperUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody String newUserDetails){
        logger.info("Payload: " + newUserDetails);
        JsonNode userDetailNode = null;

        try {
            userDetailNode = mapperUtil.convertStringToTree(newUserDetails);
        }catch (JsonProcessingException exception){
            logger.error(String.format("Exception %s",exception.getMessage()));
        }

        if(userDetailNode!=null){
            UserRecord userRecord = userService.createUser(userDetailNode);
            if(userRecord!=null)
                return ResponseEntity.ok("User Registered");
        }

        return ResponseEntity.badRequest().body("Bad Request!!");
    }
}
