package com.project_management.services;

import com.project_management.dto.auth.LoginRequest;
import com.project_management.dto.auth.LoginResponse;
import com.project_management.dto.auth.register.RegisterRequest;
import com.project_management.dto.auth.register.RegisterResponse;
import com.project_management.dto.users.ViewUserDto;
import com.project_management.entities.UserEntity;
import com.project_management.repositories.UserRepository;
import com.project_management.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegisterResponse register(@Valid RegisterRequest request) {


        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists"); // will change the error type later
        }
        UserEntity user = new UserEntity();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setDesignation(request.getDesignation());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole("USER");

        log.debug("Saving user with email: {}", user.getEmail());

        UserEntity savedUser = userRepository.save(user);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(user.getId());
        registerResponse.setFirstName(user.getFirstName());
        registerResponse.setLastName(user.getLastName());
        registerResponse.setEmail(user.getEmail());
        registerResponse.setDesignation(user.getDesignation());
        registerResponse.setCreatedAt(user.getCreatedAt());
        registerResponse.setUpdatedAt(user.getUpdatedAt());



        log.info("User saved successfully with id: {}", savedUser.getId());
        return registerResponse;
    }


    public LoginResponse login (LoginRequest loginRequest) {
//        System.out.println("Before authenticate()");
//        System.out.println(authenticationManager.getClass());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

//        we are setting authentication in SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        principal() is current user
        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());



        System.out.println("after authenticate()");
        System.out.println(authentication.isAuthenticated() + "is authenticated");
        System.out.println(authentication.getPrincipal() + "is principal");
        System.out.println(authentication.getAuthorities() + " auth");
        return new LoginResponse(token);
    }
}
