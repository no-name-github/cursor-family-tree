package com.familytree.familytree.exception;

public abstract class FamilyTreeException extends RuntimeException {
    private final String errorCode;

    protected FamilyTreeException(String message) {
        super(message);
        this.errorCode = this.getClass().getSimpleName();
    }

    protected FamilyTreeException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
} 