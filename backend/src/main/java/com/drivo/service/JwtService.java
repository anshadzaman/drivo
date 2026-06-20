package com.drivo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${JWT_SECRET}")
    private String secret;

    private static final long
            TOKEN_VALIDITY =
            24 * 60 * 60 * 1000;

    public String generateToken(

            Long id,

            String email,

            String role

    ) {

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
                                        +
                                        TOKEN_VALIDITY
                        )
                )

                .signWith(
                        Keys.hmacShaKeyFor(
                                secret.getBytes()
                        ),
                        SignatureAlgorithm.HS256
                )

                .compact();

    }

    private Claims getClaims(
            String token
    ) {

        return Jwts.parserBuilder()

                .setSigningKey(
                        Keys.hmacShaKeyFor(
                                secret.getBytes()
                        )
                )

                .build()

                .parseClaimsJws(
                        token
                )

                .getBody();

    }

    public String extractEmail(
            String token
    ) {

        return getClaims(
                token
        ).getSubject();

    }

    public String extractRole(
            String token
    ) {

        return getClaims(
                token
        ).get(
                "role",
                String.class
        );

    }

    public Long extractId(
            String token
    ) {

        return getClaims(
                token
        ).get(
                "id",
                Long.class
        );

    }

}