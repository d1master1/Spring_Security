package com.example.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    BCryptPasswordEncoder PasswordEncoder() { return  new BCryptPasswordEncoder(); }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/registration", "/login", "/webjars/**", "/css/**", "/js/**", "/images/**", "/fonts/**", "/save", "/profile")
                        .permitAll()
                        .requestMatchers("/book/**")
                        .authenticated()
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }
}