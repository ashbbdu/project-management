package com.project_management.controllers;

import com.project_management.advices.ApiResponse;
import com.project_management.dto.project.CreateProjectRequest;
import com.project_management.dto.project.ProjectResponse;
import com.project_management.services.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/{projectId}")
    public ResponseEntity<ApiResponse<ProjectResponse>> getProjectById (@PathVariable Long projectId) {
        ProjectResponse project = projectService.getProjectById(projectId);

        ApiResponse<ProjectResponse> apiResponse = ApiResponse.success("Project fetched successfully !" , project);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
