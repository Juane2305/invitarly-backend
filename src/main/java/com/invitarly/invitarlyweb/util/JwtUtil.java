package com.invitarly.invitarlyweb.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;  // <-- Usamos Base64 nativo de Java 11+
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretBase64;

    @Value("${jwt.expirationMs:3600000}")
    private long expirationMs;

    private byte[] secretBytes;

    @PostConstruct
    public void init() {
        this.secretBytes = Base64.getDecoder().decode(secretBase64);
    }

    public String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        // En 0.9.1, signWith recibe (SignatureAlgorithm alg, byte[] key)
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretBytes)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(secretBytes)
                .parseClaimsJws(token);

        return jws.getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(secretBytes)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        return expiration.before(new Date());
    }
}