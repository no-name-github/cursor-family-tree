package com.familytree.familytree.exception;

/**
 * Exception thrown when a person is not found in the system.
 * <p>
 * This exception is used to indicate that a requested person does not exist
 * in the database or cannot be found by the specified criteria.
 * </p>
 *
 * @author Family Tree Team
 * @version 1.0
 */
public class PersonNotFoundException extends FamilyTreeException {
    /**
     * Constructs a new PersonNotFoundException with the specified message.
     *
     * @param message the detail message
     */
    public PersonNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new PersonNotFoundException with the specified message and error code.
     *
     * @param message the detail message
     * @param errorCode the error code
     */
    public PersonNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
} 