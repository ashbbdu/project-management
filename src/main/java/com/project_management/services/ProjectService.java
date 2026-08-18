package com.project_management.services;

import com.project_management.dto.project.CreateProjectRequest;
import com.project_management.dto.project.ProjectResponse;
import com.project_management.dto.project.ProjectTaskDto;
import com.project_management.entities.Project;

import java.util.List;

public interface ProjectService {
    public ProjectResponse createProject (CreateProjectRequest createProjectRequest);

    ProjectResponse getProjectById(Long projectId);


    List<ProjectTaskDto> testNPlusOne ();

}
