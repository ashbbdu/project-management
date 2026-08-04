package com.project_management.services;

import com.project_management.dto.auth.LoginRequest;
import com.project_management.dto.auth.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public LoginResponse login (LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
        );

        System.out.println(authentication.isAuthenticated() + "is authenticated");
        System.out.println(authentication.getPrincipal() + "is principal");
        return new LoginResponse("User Logged in Successfully");
    }

}
