package com.invitarly.invitarlyweb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${admin.user}")
    private String adminUser;

    @Value("${admin.pass}")
    private String adminPass;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals(adminUser)) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        return new User(
                adminUser,
                "{noop}" + adminPass,
                Collections.emptyList()
        );
    }
}