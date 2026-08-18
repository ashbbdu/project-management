package com.project_management.controllers;

import com.project_management.advices.ApiResponse;
import com.project_management.dto.project.CreateProjectRequest;
import com.project_management.dto.project.ProjectResponse;
import com.project_management.dto.project.ProjectTaskDto;
import com.project_management.entities.Project;
import com.project_management.services.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/test-n-plus-one")
    public ResponseEntity<ApiResponse<List<ProjectTaskDto>>> testNPlusOne() {

        List<ProjectTaskDto> projects = projectService.testNPlusOne();

        ApiResponse<List<ProjectTaskDto>> apiResponse = ApiResponse.success("Products Fetched Successfully !" , projects);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
