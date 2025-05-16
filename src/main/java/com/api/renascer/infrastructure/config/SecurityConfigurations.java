package com.api.renascer.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/renascer-api/user").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/renascer-api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/renascer-api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/renascer-api/auth/check-email/{email}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/renascer-api/auth/recover-password/{email}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/renascer-api/video/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/renascer-api/video/search-videos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/renascer-api/video/latest-videos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/renascer-api/video/all-by-category").permitAll()
                        .requestMatchers(HttpMethod.GET, "/renascer-api/video/last-video").permitAll()
                        .requestMatchers(HttpMethod.GET, "/renascer-api/schedule/all-schedule-valid-deadline").permitAll()
                        .requestMatchers(HttpMethod.POST, "/renascer-api/schedule/all-by-date").permitAll()
                        .requestMatchers(HttpMethod.POST, "/renascer-api/schedule/generate-schedule").permitAll()
                        .requestMatchers(HttpMethod.POST, "/renascer-api/notification/expo").permitAll()
                        .requestMatchers(HttpMethod.POST, "/renascer-api/notification/generate").permitAll()
                        .requestMatchers(HttpMethod.GET, "/renascer-api/bible/books").permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
