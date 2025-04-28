package com.familytree.familytree.exception;

import com.familytree.familytree.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the Family Tree application.
 * <p>
 * This class provides centralized exception handling for the application.
 * It converts exceptions to appropriate HTTP responses with error details.
 * </p>
 *
 * @author Family Tree Team
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles FamilyTreeException and its subclasses.
     * <p>
     * This method converts FamilyTreeException to an appropriate HTTP response.
     * For PersonNotFoundException, it returns HTTP 404 (Not Found).
     * For other FamilyTreeException subclasses, it returns HTTP 400 (Bad Request).
     * </p>
     *
     * @param ex the exception to handle
     * @param request the HTTP request
     * @return a ResponseEntity containing the error details
     */
    @ExceptionHandler(FamilyTreeException.class)
    public ResponseEntity<ErrorResponse> handleFamilyTreeException(
            FamilyTreeException ex, HttpServletRequest request) {
        HttpStatus status = ex instanceof PersonNotFoundException ? 
                HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .errorCode(ex.getErrorCode())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorCode("ILLEGAL_ARGUMENT")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MethodArgumentNotValidException.
     * <p>
     * This method handles validation errors and returns HTTP 400 (Bad Request)
     * with details about the validation failures.
     * </p>
     *
     * @param ex the exception to handle
     * @param request the HTTP request
     * @return a ResponseEntity containing the validation error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorCode("VALIDATION_ERROR")
                .message("Validation failed")
                .path(request.getRequestURI())
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all other exceptions.
     * <p>
     * This method is a catch-all for any unhandled exceptions and returns
     * HTTP 500 (Internal Server Error).
     * </p>
     *
     * @param ex the exception to handle
     * @param request the HTTP request
     * @return a ResponseEntity containing the error details
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .errorCode("INTERNAL_SERVER_ERROR")
                .message("An unexpected error occurred")
                .path(request.getRequestURI())
                .build();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 