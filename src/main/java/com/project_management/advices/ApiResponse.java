package com.project_management.advices;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class ApiResponse <T> {
    private LocalDateTime timeStamp;
    private Boolean success;
    private String message;
    private ApiError error;
    private T data;

    ApiResponse () {
        this.timeStamp = LocalDateTime.now();
    }

    ApiResponse (ApiError error) {
        this();
        this.success = false;
        this.message = "Something went wrong";
        this.error = error;
    }

    ApiResponse(T data) {
        this();
        this.success = true;
        this.message = "Successful Operation";
        this.data = data;
    }

    public ApiResponse(LocalDateTime timeStamp,
                       Boolean success,
                       String message,
                       ApiError error,
                       T data) {
        this.timeStamp = timeStamp;
        this.success = success;
        this.message = message;
        this.error = error;
        this.data = data;
    }
}
