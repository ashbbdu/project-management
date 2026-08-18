package com.project_management.dto.project;

import com.project_management.dto.types.ProjectStatus;
import com.project_management.entities.TaskEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProjectTaskDto {
    private Long id;
    private String name;

    private String description;

    private ProjectStatus status;

    private List<TaskDto> tasks;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
