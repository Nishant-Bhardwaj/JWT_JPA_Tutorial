package com.nishant.jpa.complete.tutorial.config;

import com.nishant.jpa.complete.tutorial.model.AuthRequest;
import com.nishant.jpa.complete.tutorial.model.UserRecord;
import com.nishant.jpa.complete.tutorial.service.AuthRequestService;
import com.nishant.jpa.complete.tutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AuthRequestService authRequestService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,BadCredentialsException {

        AuthRequest userInfo = authRequestService.getUserRecordByUsername(username);

        if(username.equals(userInfo.getUsername()))
            return new User(username, passwordEncoder.encode(userInfo.getPassword()), new ArrayList<>());
        else
            throw new BadCredentialsException("Bad Credentials");
    }
}
