package com.project_management.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader =
                request.getHeader("Authorization");
        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;


        }
        String jwt =
                authHeader.substring(7);

        System.out.println(jwt + " this is token");
        String userName = jwtService.extractSubject(jwt);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

        System.out.println(jwtService.isTokenValid(jwt , userDetails) + " is token valid out");
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println(jwtService.isTokenValid(jwt , userDetails) + " is token valid in");
            if(jwtService.isTokenValid(jwt , userDetails)) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                               userDetails,
//                                userDetails.getPassword(),
                                null,
                                userDetails.getAuthorities()
                        )
                ;
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
