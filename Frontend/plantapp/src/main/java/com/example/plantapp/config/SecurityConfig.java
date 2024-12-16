package com.example.plantapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**").permitAll() // Halaman publik
                .anyRequest().permitAll() // Semua URL dapat diakses tanpa login
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true) // Arahkan ke homepage setelah login
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/") // Arahkan ke homepage setelah logout
                .permitAll()
                .and()
                .csrf().disable(); // Nonaktifkan CSRF untuk pengujian

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}