package com.nishant.jpa.complete.tutorial.controller;

import com.nishant.jpa.complete.tutorial.config.MyUserDetailsService;
import com.nishant.jpa.complete.tutorial.model.AuthRequest;
import com.nishant.jpa.complete.tutorial.model.AuthResponse;
import com.nishant.jpa.complete.tutorial.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/login")
    public ResponseEntity<?> token(@RequestBody AuthRequest authRequest) throws BadCredentialsException{
        logger.info("Req. name: "+ authRequest.getUsername() + " pass: "+ authRequest.getPassword());

        try {

            //Authenticate Username and Password:

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        }catch (Exception e){
            logger.error("Exception: "+e.getMessage());
            throw new BadCredentialsException("Bad Credentials!!");
        }

        // If no exception/ user authenticated :
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUsername());

        // Generate token:
        String token = jwtUtil.generateToken(userDetails);

        logger.info("Token: "+ token);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @RequestMapping("/msg")
    public String welcome(){
        return "Test";
    }
}
