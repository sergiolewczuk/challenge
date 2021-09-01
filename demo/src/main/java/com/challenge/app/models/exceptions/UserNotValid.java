package com.challenge.app.models.exceptions;

public class UserNotValid  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotValid() {
        super();
    }

    public UserNotValid(String message) {
        super(message);
    }
}