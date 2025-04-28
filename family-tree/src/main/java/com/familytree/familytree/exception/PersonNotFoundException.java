package com.familytree.familytree.exception;

public class PersonNotFoundException extends FamilyTreeException {
    public PersonNotFoundException(String message) {
        super(message);
    }

    public PersonNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }
} 