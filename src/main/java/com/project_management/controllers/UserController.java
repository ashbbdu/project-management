package com.project_management.controllers;

import com.project_management.advices.ApiResponse;
import com.project_management.dto.users.CreateUserDto;
import com.project_management.dto.users.ViewUserDto;
import com.project_management.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
@Slf4j // we need not to configure this because we are using lombok
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/health")
    public ResponseEntity<ApiResponse<String>> health () {
        ApiResponse apiResponse = ApiResponse.builder().message("Health is fine").success(true).data("fine").timeStamp(LocalDateTime.now()).build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ApiResponse<ViewUserDto>> createUser (@RequestBody @Validated CreateUserDto createUserDto) {
        log.info("Received request to create user with email: {}", createUserDto.getEmail());
        ViewUserDto user = userService.createUser(createUserDto);
        log.info("User created successfully with id: {}", user.getId());
        ApiResponse<ViewUserDto> apiResponse = ApiResponse.<ViewUserDto>builder()
                .message("User Created Successfully !")
                .success(true)
                .data(user)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
