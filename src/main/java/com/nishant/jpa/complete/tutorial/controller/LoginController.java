package com.nishant.jpa.complete.tutorial.controller;

import com.nishant.jpa.complete.tutorial.model.AuthRequest;
import com.nishant.jpa.complete.tutorial.model.AuthResponse;
import com.nishant.jpa.complete.tutorial.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> token(String username, String password) throws BadCredentialsException {

        logger.info(String.format("requestPayload: username: %s password: %s", username, password));

        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(username);
        authRequest.setPassword(password);

        String tokenValue = loginService.generateToken(authRequest);

        if(tokenValue==null){
            return ResponseEntity.badRequest().body(new AuthResponse("Bad Credentials!!"));
        }

        return ResponseEntity.ok(new AuthResponse(tokenValue));
    }

    @RequestMapping("/msg")
    public String welcome(){
        return "Test access via JWT token";
    }
}
