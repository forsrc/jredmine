package com.forsrc.jredmine.server.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionVo implements Serializable {

	private static final long serialVersionUID = -5381987272175642459L;

	private String status;
	private String exception;
	private String message;
	private Object args;

	public ExceptionVo() {
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public ExceptionVo setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getException() {
		return exception;
	}

	public ExceptionVo setException(String exception) {
		this.exception = exception;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ExceptionVo setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getArgs() {
		return args;
	}

	public ExceptionVo setArgs(Object args) {
		this.args = args;
		return this;
	}



}
