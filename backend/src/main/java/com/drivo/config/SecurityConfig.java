package com.drivo.config;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import com.drivo.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                HttpMethod.OPTIONS,
                                "/**"
                        )
                        .permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/users"
                        )
                        .permitAll()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/users/login"
                        )
                        .permitAll()
                        .requestMatchers(
                                "/ws/**"
                        )
                        .permitAll()
                        .requestMatchers(
                                "/shipments/*/my-deliver"
                        )
                        .hasRole("DRIVER")
                        .requestMatchers(
                                "/shipments/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "SHOP",
                                "DRIVER"
                        )
                        .requestMatchers(
                                "/notifications/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "SHOP",
                                "DRIVER"
                        )
                        .requestMatchers(
                                HttpMethod.GET,
                                "/users"
                        )
                        .hasRole("ADMIN")

                        .requestMatchers(
                                "/users/count",
                                "/users/count/drivers",
                                "/users/count/shops"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "SHOP",
                                "DRIVER"
                        )
                        .anyRequest()
                        .authenticated()


                );

        http.addFilterBefore(

                jwtFilter,

                UsernamePasswordAuthenticationFilter.class

        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }
    @Bean
    public CorsConfigurationSource
    corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOriginPatterns(
                List.of("https://drivopro.vercel.app",
                        "http://localhost:4200"
                        )
        );

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        configuration.setAllowCredentials(
                true
        );

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}