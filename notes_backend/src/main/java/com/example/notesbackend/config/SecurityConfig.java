package com.example.notesbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * PUBLIC_INTERFACE
 * Basic Spring Security configuration. Currently permits all requests while providing a PasswordEncoder bean.
 * TODO: Protect /notes endpoints and issue JWTs for authenticated access in future iteration.
 */
@Configuration
public class SecurityConfig {

    // PUBLIC_INTERFACE
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // PUBLIC_INTERFACE
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // Allow H2 console
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/", "/health", "/api/info", "/docs", "/swagger-ui.html", "/swagger-ui/**", "/api-docs/**", "/v3/api-docs/**", "/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/notes/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/notes").permitAll()
                .requestMatchers(HttpMethod.PUT, "/notes/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/notes/**").permitAll()
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults()); // available if needed
        return http.build();
    }
}
