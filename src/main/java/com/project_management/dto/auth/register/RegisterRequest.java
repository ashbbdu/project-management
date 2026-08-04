package com.project_management.dto.auth.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterRequest {
//    private String email;
//    private String password;
//    private String firstName;
//    private String lastName;

    @NotNull(message = "Email is required !")
    @NotBlank(message = "Email is required !")
    @Email(message = "Invalid Email !")
    private String email;
    @NotNull(message = "First Name is required !")
    @NotBlank(message = "First Name is required !")
    @Length(min = 4 , max = 10 , message = "Length")
    private String firstName;
    @NotNull(message = "Last Name is required !")
    @NotBlank(message = "Last Name is required !")
    @Length(min = 4 , max = 10 , message = "Length")
    private String lastName;
    @NotNull(message = "Password is required !")
    @NotBlank(message = "Password is required !")
    @Length(min = 4 , max = 10 , message = "Length")
    private String password;
    @NotNull(message = "Designation is required !")
    @NotBlank(message = "Designation is required !")
    private String designation;
}
