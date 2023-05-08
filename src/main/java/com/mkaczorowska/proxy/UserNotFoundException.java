package com.mkaczorowska.proxy;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("Cannot find user " + username);
    }
}