package com.forsrc.jredmine.server.aop;

import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LogAspect {

	private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

	private static final ThreadLocal<UUID> THREAD_LOCAL_UUID = new ThreadLocal<>();

	@Before(value = "execution(* com.forsrc..*Controller.*(..))")
	public void before(JoinPoint joinPoint) {
		UUID uuid = getUuid();
		THREAD_LOCAL_UUID.set(uuid);
		LOG.info("[START]\t{} {} [Controller]\t{}; {}; {};", getSessionId(), uuid, joinPoint.getSignature(),
				joinPoint.getArgs(), joinPoint.getTarget());
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
			LOG.warn("[Around]\t{} {} [Controller Exception] Time: {}ms; Exception: {}, {};", getSessionId(), uuid,
					time, e.getClass().getName(), e.getMessage());
			// Map<String, Object> message = new HashMap<>(3);
			// message.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
			// message.put("message", e.getMessage());
			// message.put("args", joinPoint.getArgs());
			// message.put("exception", e.getClass().getName());
			// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
			e.printStackTrace();
			throw e;

		}
		Long time = System.currentTimeMillis() - start;
		LOG.info("[Around]\t{} {} [Controller]\tTime: {}ms; {}", getSessionId(), uuid, time, rt);
		return rt;
	}

	@After(value = "execution(* com.forsrc..*Controller.*(..))")
	public void after(JoinPoint joinPoint) {
		UUID uuid = getUuid();
		// LOG.info("[END]---------------> {}; {}; {}; {}; {}", uuid,
		// joinPoint.getSignature(), joinPoint.getArgs(), joinPoint.getTarget(),
		// joinPoint);
		LOG.info("[END]\t{} {} [Controller]", getSessionId(), uuid);

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
		LOG.info("[START]\t{} {} [Service]\t\t{}; {}; {}; {}", getSessionId(), uuid, joinPoint.getSignature(),
				joinPoint.getArgs(), joinPoint.getTarget());
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
			LOG.warn("[Around]\t{} {} [Service Exception]\tTime: {}ms; Exception: {}, {};", getSessionId(), uuid, time,
					e.getClass().getName(), e.getMessage());

			throw e;

		}
		Long time = System.currentTimeMillis() - start;
		LOG.info("[Around]\t{} {} [Service]\t\tTime: {}ms; {}", getSessionId(), uuid, time, rt);
		return rt;
	}

	@After(value = "execution(* com.forsrc..*Service.*(..))")
	public void afterService(JoinPoint joinPoint) {
		UUID uuid = getUuid();
		LOG.info("[END]\t{} {} [Service]", getSessionId(), uuid);

	}

	private String getSessionId() {

		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (servletRequestAttributes != null) {
			return servletRequestAttributes.getRequest().getSession().getId();
		}
		return "";
	}
}
