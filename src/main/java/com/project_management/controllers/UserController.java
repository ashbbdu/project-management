package com.project_management.controllers;

import com.project_management.advices.ApiResponse;
import com.project_management.dto.users.CreateUserDto;
import com.project_management.dto.users.ViewUserDto;
import com.project_management.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j // we need not to configure this because we are using lombok
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/create")
    public ResponseEntity<ApiResponse<ViewUserDto>> createUser (@RequestBody @Valid CreateUserDto createUserDto) {
        log.info("Received request to create user with email: {}", createUserDto.getEmail());
        ViewUserDto user = userService.createUser(createUserDto);
        log.info("User created successfully with id: {}", user.getId());
//        ApiResponse<ViewUserDto> apiResponse = ApiResponse.<ViewUserDto>builder()
//                .message("User Created Successfully !")
//                .success(true)
//                .data(user)
//                .timeStamp(LocalDateTime.now())
//                .build();

//        return ResponseEntity.ok(ApiResponse.success); // to make thi work we have to make success() a static method
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("User Created Successfully !" , user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ViewUserDto>>> list () {
        List<ViewUserDto> user = userService.list();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Users Fetched Successfully !" , user));
    }

}
