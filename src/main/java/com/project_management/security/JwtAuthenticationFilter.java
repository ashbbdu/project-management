package com.project_management.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
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

            System.out.println("USER = " + userDetails.getUsername());
            System.out.println("AUTHORITIES = " + userDetails.getAuthorities());

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
                            );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println(
                            "Context Authentication = " +
                                    SecurityContextHolder.getContext().getAuthentication()
                    );
                }
            }
//            filterChain.doFilter(request, response); // should be outside try catch

        }
//        catch (Exception ex) {
//            System.out.println("EXCEPTION TYPE = " + ex.getClass().getName());
//            System.out.println("EXCEPTION MESSAGE = " + ex.getMessage());
//            handlerExceptionResolver.resolveException(request , response , null , ex);
//        }
        catch (ExpiredJwtException ex) {

            jwtAuthenticationEntryPoint.commence(
                    request,
                    response,
                    new BadCredentialsException("Token Expired")
            );

        } catch (Exception ex) {

            jwtAuthenticationEntryPoint.commence(
                    request,
                    response,
                    new BadCredentialsException("Invalid Token")
            );
        }
        filterChain.doFilter(request, response);
    }

}
