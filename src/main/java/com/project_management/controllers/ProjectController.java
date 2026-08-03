package com.project_management.controllers;

import com.project_management.advices.ApiResponse;
import com.project_management.dto.project.CreateProjectRequest;
import com.project_management.dto.project.ProjectResponse;
import com.project_management.services.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> create (@RequestBody @Valid CreateProjectRequest createProjectRequest) {
        ProjectResponse projectResponse = projectService.createProject(createProjectRequest);
        ApiResponse<ProjectResponse> apiResponse = ApiResponse.success("Project created successfully !" , projectResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
