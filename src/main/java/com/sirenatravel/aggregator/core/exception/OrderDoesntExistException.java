package com.sirenatravel.aggregator.core.exception;

public class OrderDoesntExistException  extends RuntimeException{

    public OrderDoesntExistException() {
    }

    public OrderDoesntExistException(String message) {
        super(message);
    }

    public OrderDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
