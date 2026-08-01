package com.project_management.dto.users;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateUserDto {
    @NotNull(message = "Email is required !")
    @NotBlank(message = "Email is required !")
    private String email;
    @NotNull(message = "First Name is required !")
    @NotBlank(message = "First Name is required !")
    @Length(min = 4 , max = 10 , message = "Length")
    private String firstName;
    @NotNull(message = "Last Name is required !")
    @NotBlank(message = "Last Name is required !")
    @Length(min = 4 , max = 10 , message = "Length")
    private String lastName;
    @NotNull(message = "Designation is required !")
    @NotBlank(message = "Designation is required !")
    private String designation;
}
