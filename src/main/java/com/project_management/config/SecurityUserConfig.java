package com.project_management.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//class created to create InMemoryUsers
@Configuration
public class SecurityUserConfig {

//    @Bean
    UserDetailsService userDetailsService () {
        UserDetails adminUser = User.builder().username("admin user").password(passwordEncoder().encode("abcde")).roles("ADMIN").build();
        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("abcde")).roles("USER").build();
        return new InMemoryUserDetailsManager(adminUser , user);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
