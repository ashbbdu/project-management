package com.project_management.controllers;

import com.project_management.advices.ApiResponse;
import com.project_management.dto.auth.LoginRequest;
import com.project_management.dto.auth.LoginResponse;
import com.project_management.dto.auth.register.RegisterRequest;
import com.project_management.dto.auth.register.RegisterResponse;
import com.project_management.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthControllers {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register (@RequestBody @Valid RegisterRequest request) {
        RegisterResponse register = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(ApiResponse.success("User registered successfully" , register));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login (@RequestBody @Valid LoginRequest request) {
        System.out.println("came inside login");
        LoginResponse login = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).
                body(ApiResponse.success("User logged in successfully" , login));
    }

}
