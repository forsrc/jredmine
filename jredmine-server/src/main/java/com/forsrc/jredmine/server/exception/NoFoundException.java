package com.forsrc.jredmine.server.exception;


public class NoFoundException extends Exception {


	private static final long serialVersionUID = 8574661626941886796L;

	public NoFoundException(String key) {
        super(String.format("No found (%s)", key));
    }
}
