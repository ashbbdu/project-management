package com.project_management.dto.project;

import com.project_management.dto.types.ProjectStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateProjectRequest {
    private String name;
    private String description;
    private ProjectStatus projectStatus; // not required because we will set it to TODO by default while creating a new project
    private LocalDate startDate;
    private LocalDate endDate;
}
