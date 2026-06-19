package com.drivo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String SECRET =
            "drivo-secret-key-drivo-secret-key";

    public String generateToken(
            Long id,
            String email,
            String role) {

        return Jwts.builder()

                .setSubject(email)

                .claim(
                        "id",
                        id
                )

                .claim(
                        "role",
                        role
                )

                .setIssuedAt(
                        new Date()
                )

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 86400000
                        )
                )

                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET.getBytes()
                        ),
                        SignatureAlgorithm.HS256
                )

                .compact();
    }
    public String extractEmail(
            String token) {

        return Jwts.parserBuilder()

                .setSigningKey(
                        Keys.hmacShaKeyFor(
                                SECRET.getBytes()))

                .build()

                .parseClaimsJws(token)

                .getBody()

                .getSubject();
    }
    public String extractRole(
            String token) {

        return Jwts.parserBuilder()

                .setSigningKey(

                        Keys.hmacShaKeyFor(
                                SECRET.getBytes())

                )

                .build()

                .parseClaimsJws(token)

                .getBody()

                .get(
                        "role",
                        String.class
                );
    }
}