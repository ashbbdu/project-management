package com.project_management.services.impl;

import com.project_management.dto.project.CreateProjectRequest;
import com.project_management.dto.project.ProjectResponse;
import com.project_management.dto.types.ProjectStatus;
import com.project_management.entities.Project;
import com.project_management.repositories.ProjectRepository;
import com.project_management.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    @Override
//    @Transactional
    public ProjectResponse createProject(CreateProjectRequest createProjectRequest) {

        System.out.println(createProjectRequest.getDescription() + " desc");
        Project project = new Project();

        project.setName(createProjectRequest.getName());
        project.setDescription(createProjectRequest.getDescription());
        project.setStartDate(createProjectRequest.getStartDate());
        project.setEndDate(createProjectRequest.getEndDate());

        // Always default to TODO
        project.setProjectStatus(ProjectStatus.TODO);

        projectRepository.save(project);

        Project savedProject = projectRepository.save(project);

        ProjectResponse response = new ProjectResponse();
        response.setId(savedProject.getId());
        response.setName(savedProject.getName());
        response.setDescription(savedProject.getDescription());
        response.setStatus(savedProject.getProjectStatus());
        response.setStartDate(savedProject.getStartDate());
        response.setEndDate(savedProject.getEndDate());
        response.setCreatedAt(savedProject.getCreatedAt());
        response.setUpdatedAt(savedProject.getUpdatedAt());

        return response;
    }
}
