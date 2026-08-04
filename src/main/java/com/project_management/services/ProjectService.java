package com.project_management.services;

import com.project_management.dto.project.CreateProjectRequest;
import com.project_management.dto.project.ProjectResponse;

public interface ProjectService {
    public ProjectResponse createProject (CreateProjectRequest createProjectRequest);

    ProjectResponse getProjectById(Long projectId);
}
