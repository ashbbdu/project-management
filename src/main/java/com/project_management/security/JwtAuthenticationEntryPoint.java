package com.project_management.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project_management.advices.ApiError;
import com.project_management.advices.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
//        String body = """
//            {
//                "status":401,
//                "error":"Unauthorized",
//                "message":"Authentication is required to access this resource."
//            }
//            """;
//        response.getWriter().write(body);


        String message = authException.getMessage();

        if (message == null || message.isBlank()) {
            message = "Token Missing";
        }

        System.out.println(message + "message");

        ApiError apiError = ApiError.builder()
                .httpStatus(HttpStatus.UNAUTHORIZED)
//                .message("Authentication is required to access this resource.")
                .message(message)
                .build();

        ApiResponse<?> apiResponse = ApiResponse.error("Authentication Failed" , apiError);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        objectMapper.writeValue(
                response.getOutputStream(),
                apiResponse
        );


    }
}
