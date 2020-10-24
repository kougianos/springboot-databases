package com.kougianos.springbootdatabases.exception;

public class InvalidOtpException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Invalid One Time Pin";

    public InvalidOtpException() {
        super(ERROR_MESSAGE);
    }
}
