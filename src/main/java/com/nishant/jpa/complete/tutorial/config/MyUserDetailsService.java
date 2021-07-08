package com.nishant.jpa.complete.tutorial.config;

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
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,BadCredentialsException {

        // will change to DB auth:
        if(username.equalsIgnoreCase("nishant"))
            return new User(username, passwordEncoder.encode("pass"), new ArrayList<>());
        else
            new BadCredentialsException("Bad Credentials");

        return null;
    }
}
