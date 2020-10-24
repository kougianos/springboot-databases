package com.kougianos.springbootdatabases.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Email already exists";

    public EmailAlreadyExistsException() {
        super(ERROR_MESSAGE);
    }
}
