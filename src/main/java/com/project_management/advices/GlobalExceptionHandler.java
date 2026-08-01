package com.project_management.advices;

import com.project_management.exceptions.ResourceNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiError> handleResourceNotFoundException (ResourceNotFoundException e) {
//        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
//        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException (ResourceNotFoundException e) {
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.BAD_REQUEST).message(e.getMessage()).build();
        return handleGlobalResponse(apiError , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolationException(
            DataIntegrityViolationException e) {

        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message("Database constraint violation")
                .subErrors(List.of(e.getMostSpecificCause().getMessage()))
                .build();

        return handleGlobalResponse(apiError , HttpStatus.CONFLICT);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> handleMethodNotValidException (MethodArgumentNotValidException ex) {
//        List<String> errors = ex.getAllErrors().stream().map(res -> res.getDefaultMessage()).toList();
//        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.BAD_REQUEST).subErrors(errors).build();
//        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodNotValidException (MethodArgumentNotValidException ex) {
        List<String> errors = ex.getAllErrors().stream().map(res -> res.getDefaultMessage()).toList();
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.BAD_REQUEST).message("Input validation failed").subErrors(errors).build();
        return handleGlobalResponse(apiError , HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse<?>> handleGlobalResponse (ApiError apiError , HttpStatus status) {
        return new ResponseEntity<>(new ApiResponse<>(apiError) , status);
    }
}
