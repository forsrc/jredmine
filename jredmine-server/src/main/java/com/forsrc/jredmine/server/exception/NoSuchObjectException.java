package com.forsrc.jredmine.server.exception;


public class NoSuchObjectException extends Exception {


	private static final long serialVersionUID = 8574661626941886796L;

	public NoSuchObjectException(String key) {
        super(String.format("No such object (%s)", key));
    }
}
