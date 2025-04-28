package com.familytree.familytree.exception;

/**
 * Base exception class for the Family Tree application.
 * <p>
 * This class serves as the parent class for all custom exceptions in the application.
 * It provides a common structure for exception handling and error reporting.
 * </p>
 *
 * @author Family Tree Team
 * @version 1.0
 */
public class FamilyTreeException extends RuntimeException {
    private String errorCode;

    /**
     * Constructs a new FamilyTreeException with the specified message.
     *
     * @param message the detail message
     */
    public FamilyTreeException(String message) {
        super(message);
    }

    /**
     * Constructs a new FamilyTreeException with the specified message and error code.
     *
     * @param message the detail message
     * @param errorCode the error code
     */
    public FamilyTreeException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Gets the error code associated with this exception.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }
} 