package com.luan.exception;

/* represents a valid query that did not find the requested user */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id) {
        super("User with id " + id + " was not found");
    }
}
