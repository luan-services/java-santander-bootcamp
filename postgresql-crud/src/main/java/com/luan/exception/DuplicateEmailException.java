package com.luan.exception;

/* represents an email rejected by the unique constraint in PostgreSQL */
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email, Throwable cause) {
        super("A user with email " + email + " already exists", cause);
    }
}
