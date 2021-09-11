package com.forsrc.jredmine.server.aop;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.forsrc.jredmine.server.exception.NoFoundException;
import com.forsrc.jredmine.server.exception.NoSuchObjectException;
import com.forsrc.jredmine.server.vo.ExceptionVo;

@RestControllerAdvice
public class ExceptionRestControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionVo exception(Exception e, WebRequest request) {
		ExceptionVo vo = new ExceptionVo()
				.setException(e.getClass().getName())
				.setMessage(e.getMessage())
				.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString())
				;
		
		return vo;
	}

	@ExceptionHandler({ NoSuchObjectException.class, NoSuchElementException.class, NoFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionVo notFound(Exception e, WebRequest request) {
		ExceptionVo vo = new ExceptionVo()
				.setException(e.getClass().getName())
				.setMessage(e.getMessage())
				.setStatus(HttpStatus.NOT_FOUND.toString())
				;

		return vo;
	}
	
 
}
