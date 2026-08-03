package com.project_management.dto.project;

import com.project_management.dto.types.ProjectStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateProjectRequest {
    private String name;
    private String description;
    private ProjectStatus projectStatus;

    private LocalDate startDate;
    private LocalDate endDate;
}
