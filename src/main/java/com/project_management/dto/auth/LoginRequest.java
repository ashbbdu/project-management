package com.project_management.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Required Field")
    @NotNull(message = "Required Field")
    private String email;
    @NotBlank(message = "Required Field")
    @NotNull(message = "Required Field")
    private String password;
}
