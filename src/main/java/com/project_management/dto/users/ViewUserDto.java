package com.project_management.dto.users;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewUserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String designation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
