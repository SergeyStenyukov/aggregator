package com.sirenatravel.aggregator.core.exception;

public class CompanyDoesntExistException extends RuntimeException {

    public CompanyDoesntExistException() {
    }

    public CompanyDoesntExistException(String message) {
        super(message);
    }

    public CompanyDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
