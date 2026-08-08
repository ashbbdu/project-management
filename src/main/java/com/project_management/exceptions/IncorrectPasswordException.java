package com.project_management.exceptions;

import javax.naming.AuthenticationException;

public class IncorrectPasswordException extends AuthenticationException {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
