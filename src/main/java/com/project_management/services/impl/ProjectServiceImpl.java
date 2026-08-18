package com.project_management.services.impl;

import com.project_management.dto.project.CreateProjectRequest;
import com.project_management.dto.project.ProjectResponse;
import com.project_management.dto.project.ProjectTaskDto;
import com.project_management.dto.project.TaskDto;
import com.project_management.dto.types.ProjectStatus;
import com.project_management.dto.types.TaskStatus;
import com.project_management.entities.Project;
import com.project_management.repositories.ProjectRepository;
import com.project_management.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.config.Task;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('PROJECT_CREATE')")
    public ProjectResponse createProject(CreateProjectRequest createProjectRequest) {

        System.out.println(createProjectRequest.getDescription() + " desc");
        Project project = new Project();

        project.setName(createProjectRequest.getName());
        project.setDescription(createProjectRequest.getDescription());
        project.setStartDate(createProjectRequest.getStartDate());
        project.setEndDate(createProjectRequest.getEndDate());

        // Always default to TODO
        project.setProjectStatus(ProjectStatus.TODO);

//        projectRepository.save(project);

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

    @Override
    @PreAuthorize("hasAuthority('PROJECT_UPDATE')")
    public ProjectResponse getProjectById(Long projectId) {
        return null;
    }

    @Transactional(readOnly = true)
    public List<ProjectTaskDto> testNPlusOne() {

//        List<Project> projects = projectRepository.findAll();

//        for (Project project : projects) {
//            System.out.println(
//                    project.getName() + " -> " +
//                            project.getTasks().size()
//            );
//        }

//        List<Project> projects = projectRepository.findAllWithTasks();
        List<Project> projects = projectRepository.findAll();

        List<ProjectTaskDto> response = projects.stream().map(project -> {
            ProjectTaskDto projectTaskDto = new ProjectTaskDto();
            projectTaskDto.setId(project.getId());
            projectTaskDto.setName(project.getName());
            projectTaskDto.setDescription(project.getDescription());
            projectTaskDto.setStatus(project.getProjectStatus());
            projectTaskDto.setStartDate(project.getStartDate());
            projectTaskDto.setEndDate(project.getEndDate());
            projectTaskDto.setCreatedAt(project.getCreatedAt());
            projectTaskDto.setUpdatedAt(project.getUpdatedAt());

           List<TaskDto> tasks = project.getTasks().stream().map(task -> {
                        TaskDto taskDto = new TaskDto();
                        taskDto.setDescription(task.getDescription());
                        taskDto.setTitle(task.getTitle());
                        return taskDto;
                    }).toList();

            projectTaskDto.setTasks(tasks);

            return projectTaskDto;
        }).toList();



        return response;
    }
}
