package com.invitarly.invitarlyweb.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Servicio que retorna un usuario "admin" hardcodeado,
 * sólo para probar.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Ejemplo: si el username es "admin", retornamos un User con password "admin123".
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Roles vacíos o admin
        return new User(
                "admin",
                "{noop}admin123", // {noop} indica sin encriptar (no para prod)
                Collections.emptyList()
        );
    }
}