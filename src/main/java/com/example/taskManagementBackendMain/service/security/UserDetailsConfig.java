package com.example.taskManagementBackendMain.service.security;

import com.example.taskManagementBackendMain.entity.User;
import com.example.taskManagementBackendMain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Configuration
public class UserDetailsConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {

            User user = userRepository.findByEmail(username);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singleton(
                            new SimpleGrantedAuthority("USER")
                    )
            );
        };
    }
}