package com.familytree.familytree.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for error responses.
 * <p>
 * This class represents a standardized error response that is returned to clients
 * when an exception occurs in the application.
 * </p>
 *
 * @author Family Tree Team
 * @version 1.0
 */
@Data
@Builder
public class ErrorResponse {
    /**
     * The timestamp when the error occurred.
     */
    private LocalDateTime timestamp;

    /**
     * The HTTP status code of the error.
     */
    private int status;

    /**
     * The HTTP status message of the error.
     */
    private String error;

    /**
     * The application-specific error code.
     */
    private String errorCode;

    /**
     * A detailed error message.
     */
    private String message;

    /**
     * The path of the request that caused the error.
     */
    private String path;
} 