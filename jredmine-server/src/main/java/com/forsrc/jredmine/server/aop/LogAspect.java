package com.forsrc.jredmine.server.aop;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
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
	
	private static final ThreadLocal<UUID> THREAD_LOCAL_UUID = new ThreadLocal<>();
	
	@Before(value = "execution(* com.forsrc..*Controller.*(..))")
	public void before(JoinPoint joinPoint) {
		UUID uuid = getUuid();
		THREAD_LOCAL_UUID.set(uuid);
		LOG.info("[START]\t[Controller] {}; {}; {}; {}; {}", uuid, joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget());
	}
	
	@Around(value = "execution(* com.forsrc..*Controller.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		UUID uuid = getUuid();
		Object rt = null;
		try {
			rt = joinPoint.proceed();
		} catch (Throwable e) {
			Long time = System.currentTimeMillis() - start;
			LOG.warn("[Around]\t[Controller Exception] {}; Time: {}ms; Exception: {}, {};", uuid, time, e.getClass().getName(), e.getMessage());
//			Map<String, Object> message = new HashMap<>(3);
//			message.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
//			message.put("message", e.getMessage());
//			message.put("args", joinPoint.getArgs());
//			message.put("exception", e.getClass().getName());
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
			throw e;
			
		}
		Long time = System.currentTimeMillis() - start;
		LOG.info("[Around]\t[Controller] {}; Time: {}ms; {}", uuid, time, rt);
		return rt;
	}
	
	@After(value = "execution(* com.forsrc..*Controller.*(..))")
	public void after(JoinPoint joinPoint) {
		UUID uuid = getUuid();
		// LOG.info("[END]---------------> {}; {}; {}; {}; {}", uuid, joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget(), joinPoint);
		LOG.info("[END]\t[Controller] {};", uuid);

	}

	private UUID getUuid() {
		UUID uuid = THREAD_LOCAL_UUID.get();
		if (uuid == null) {
			synchronized (THREAD_LOCAL_UUID) {
				uuid = UUID.randomUUID();
				THREAD_LOCAL_UUID.set(uuid);
			}
		}
		return uuid;
	}
	
	
	@Before(value = "execution(* com.forsrc..*Service.*(..))")
	public void beforeService(JoinPoint joinPoint) {
		UUID uuid = getUuid();
		THREAD_LOCAL_UUID.set(uuid);
		LOG.info("[START]\t[Service] {}; {}; {}; {}; {}", uuid, joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget());
	}
	
	@Around(value = "execution(* com.forsrc..*Service.*(..))")
	public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		UUID uuid = getUuid();
		Object rt = null;
		try {
			rt = joinPoint.proceed();
		} catch (Throwable e) {
			Long time = System.currentTimeMillis() - start;
			LOG.warn("[Around]\t[Service Exception] {}; Time: {}ms; Exception: {}, {};", uuid, time, e.getClass().getName(), e.getMessage());
			throw e;
			
		}
		Long time = System.currentTimeMillis() - start;
		LOG.info("[Around]\t[Service] {}; Time: {}ms; {}", uuid, time, rt);
		return rt;
	}
	
	@After(value = "execution(* com.forsrc..*Service.*(..))")
	public void afterService(JoinPoint joinPoint) {
		UUID uuid = getUuid();
		LOG.info("[END]\t[Service] {};", uuid);

	}
}
