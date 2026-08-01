package com.project_management.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    ResourceNotFoundException (String message) {
        super(message);
    }
}
