package com.project_management.advices;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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

    private  ApiResponse(LocalDateTime timeStamp,
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

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(LocalDateTime.now() , true, message , null  , data);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(LocalDateTime.now() , true, message , null , null);
    }


    public static <T> ApiResponse<T> error(String message, ApiError error) {
        return new ApiResponse<>(LocalDateTime.now() , false, message , error , null);
    }


}
