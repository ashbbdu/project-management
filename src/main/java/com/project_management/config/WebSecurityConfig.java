package com.project_management.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                    auth

                            .requestMatchers("/hello" , "/users/**")
                            .permitAll()
                            .requestMatchers("/brands/**")
                            .authenticated()
////                                .requestMatchers("/brands/**").hasAllRoles("ADMIN" , "USER")
//
                            .anyRequest()
                            .authenticated()


                )
                .formLogin(Customizer.withDefaults())
        ;

        return http.build();
    }

//    Creating AuthenticationManager bean
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

}
