package com.sirenatravel.aggregator.api.exceptionhandler;

import com.sirenatravel.aggregator.core.exception.CompanyDoesntExistException;
import com.sirenatravel.aggregator.core.exception.EntityNotFoundException;
import com.sirenatravel.aggregator.core.exception.OrderDoesntExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(OrderDoesntExistException.class)
    public ResponseEntity<String> handleOrderDoesntExistException(OrderDoesntExistException e) {
        LOG.debug("Handle OrderDoesntExistException: {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(CompanyDoesntExistException.class)
    public ResponseEntity<String> handleOrderDoesntExistException(CompanyDoesntExistException e) {
        LOG.debug("Handle CompanyDoesntExistException: {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleOrderDoesntExistException(EntityNotFoundException e) {
        LOG.debug("Handle EntityNotFoundException: {}", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
