package com.forsrc.jredmine.server.exception;


public class NoSuchObjectException extends Exception {

    public NoSuchObjectException(String key) {
        super(String.format("No such object (%s)", key));
    }
}
