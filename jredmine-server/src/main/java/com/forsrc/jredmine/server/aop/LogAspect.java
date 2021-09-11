package com.forsrc.jredmine.server.aop;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {

	private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);
	
	@Before(value = "execution(* com.forsrc..*Controller.*(..))")
	public void before(JoinPoint joinPoint) {
		LOG.info("[START]-------------> {}; {}; {}", joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget());

	}

	@After(value = "execution(* com.forsrc..*Controller.*(..))")
	public void after(JoinPoint joinPoint) {
		LOG.info("[END]---------------> {}; {}; {}", joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget(), joinPoint);

	}
	

	
	//@Around(value = "execution(* com.forsrc..*Controller.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.currentTimeMillis();
		Object rt = null;
		try {
			rt = joinPoint.proceed();
		} catch (Throwable e) {
			Long time = System.currentTimeMillis() - start;
			LOG.warn("[Around Exception]--> {}; {}; {};\nException: {}, {};\nTime: {}ms;", joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget(), e.getClass().getName(), e.getMessage(), time);
			Map<String, Object> message = new HashMap<>(3);
			message.put("status", HttpStatus.FORBIDDEN.toString());
			message.put("message", e.getMessage());
			message.put("args", joinPoint.getArgs());
			message.put("exception", e.getClass().getName());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
			//throw e;
			
		}
		Long time = System.currentTimeMillis() - start;
		LOG.info("[Around]------------> {}; {}; {}; retrun: {}\nTime: {}ms;", joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget(), rt, time);
		return rt;
		

	}
	
}
