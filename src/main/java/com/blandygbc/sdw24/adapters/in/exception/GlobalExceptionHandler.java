package com.blandygbc.sdw24.adapters.in.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.blandygbc.sdw24.domain.exception.ChampionNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ChampionNotFoundException.class)
    public ResponseEntity<ApiError> handleDomainException(ChampionNotFoundException domainError) {
        return ResponseEntity.unprocessableEntity()
                .body(new ApiError(domainError.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleDomainException(Exception unexpetedError) {
        String message = "Ops! Ocorreu um erro inesperado.";
        logger.error(message, unexpetedError);
        return ResponseEntity.internalServerError()
                .body(new ApiError(message));
    }

    public record ApiError(String message) {
    }
}
