package com.nishant.jpa.complete.tutorial.service.impl;

import com.nishant.jpa.complete.tutorial.config.MyUserDetailsService;
import com.nishant.jpa.complete.tutorial.model.AuthRequest;
import com.nishant.jpa.complete.tutorial.service.LoginService;
import com.nishant.jpa.complete.tutorial.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public String generateToken(AuthRequest authRequest) {
        try {

            //Authenticate Username and Password:

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        }catch (Exception e){
            logger.error(String.format("Exception: %s",e.getMessage()));
            //throw new BadCredentialsException("Bad Credentials!!");
            return null;
        }

        // If no exception/ user authenticated :
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUsername());

        // Generate token:
        String token = jwtUtil.generateToken(userDetails);

        logger.info(String.format("Token: %s", token));

        return token;
    }
}
