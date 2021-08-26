package com.forsrc.jredmine.server.exception;


public class PasswordNotMatchException extends Exception {

    public PasswordNotMatchException(String username) {
        super(String.format("Password not match (%s)", username));
    }
}
