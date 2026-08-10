package com.project_management.controllers;

import com.project_management.advices.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {
    @GetMapping(path = "/test")
    public ResponseEntity<ApiResponse<String>> testTask  () {

//             LoginResponse login = authService.login(request);
//        return ResponseEntity.status(HttpStatus.OK).
//                body(ApiResponse.success("User logged in successfully" , login));
        return ResponseEntity.ok().body(ApiResponse.success("Success"));
    }
}
