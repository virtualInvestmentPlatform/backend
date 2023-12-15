package com.harun.virtualInvestmentPlatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/login", "/register", "/logout").permitAll() // Allow all users to access /login, /register, and /logout
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()).csrf(httpSecurityCsrfConfigurer -> {httpSecurityCsrfConfigurer.disable();});
        return http.build();
    }
}
