package com.project_management.advices;

import com.project_management.exceptions.IncorrectPasswordException;
import com.project_management.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
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
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
//        return handleGlobalResponse(apiError , HttpStatus.NOT_FOUND);
        return handleGlobalResponse(apiError , HttpStatus.NOT_FOUND , e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolationException(
            DataIntegrityViolationException e) {

        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message("Database constraint violation")
                .subErrors(List.of(e.getMostSpecificCause().getMessage()))
                .build();

        return handleGlobalResponse(apiError , HttpStatus.CONFLICT , "Database constraint violation");
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
        return handleGlobalResponse(apiError , HttpStatus.BAD_REQUEST , "Input validation failed");
//        return new ResponseEntity<>(apiError , HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse<?>> handleGlobalResponse (ApiError apiError , HttpStatus status , String message) {
//        return new ResponseEntity<>(new ApiResponse<>(apiError) , status);
        return new ResponseEntity<>(ApiResponse.error(message , apiError) , status);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException (AuthenticationException ex) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(ex.getLocalizedMessage())
                .build();

        return handleGlobalResponse(apiError, HttpStatus.UNAUTHORIZED , "UNAUTHORIZED");
    }
//
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> handleJwtException (JwtException ex) {
        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message(ex.getMessage())
                .build();

        return handleGlobalResponse(apiError, HttpStatus.UNAUTHORIZED , "UNAUTHORIZED");
    }
//
//    @ExceptionHandler(IncorrectPasswordException.class)
//    public ResponseEntity<ApiResponse<?>> handleIncorrectPasswordException (IncorrectPasswordException ex) {
//        ApiError apiError = ApiError.builder()
//                .httpStatus(HttpStatus.UNAUTHORIZED)
//                .message(ex.getMessage())
//                .build();
//
//        return handleGlobalResponse(apiError, HttpStatus.UNAUTHORIZED , "Incorrect Password");
//    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> handleBadCredentialsException(
            BadCredentialsException ex) {

        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .message("Incorrect password")
                .build();

        return handleGlobalResponse(
                apiError,
                HttpStatus.UNAUTHORIZED,
                "Incorrect Password"
        );
    }
}
