package com.project_management.config;

import com.project_management.security.JwtAuthenticationEntryPoint;
import com.project_management.security.JwtAuthenticationFilter;
import com.project_management.security.LoggingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {
private final JwtAuthenticationFilter jwtAuthenticationFilter;
private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
private final LoggingFilter loggingFilter;
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                    auth

                            .requestMatchers("/hello" , "/users/**" , "/auth/**")
                            .permitAll()
//                            .requestMatchers("/brands/**").authenticated()


                            .requestMatchers
                                    (HttpMethod.GET , "/project/**").hasAuthority("PROJECT_READ")
//                            .requestMatchers
//                                    (HttpMethod.POST , "/project/**").hasAuthority("PROJECT_CREATE")
                            .requestMatchers("/tasks/**").hasRole("USER")

////                                .requestMatchers("/brands/**").hasAllRoles("ADMIN" , "USER")
//
                            .anyRequest()
                            .authenticated()


                ).sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                )
                .addFilterBefore(jwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter.class)

//                .formLogin(Customizer.withDefaults())
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
