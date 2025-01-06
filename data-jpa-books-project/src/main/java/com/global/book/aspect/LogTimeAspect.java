package com.global.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order() //for priority
@Component
public class LogTimeAspect {

	Logger log = LoggerFactory.getLogger(LogTimeAspect.class);
	/*
	 * @Around(value = "execution(* com.global.book.Service..*(..))") public void
	 * logTime(JoinPoint joinPoint) { long startTime = System.currentTimeMillis();
	 * 
	 * StringBuilder stringBuilder = new StringBuilder("KPI:");
	 * stringBuilder.append("[").append(joinPoint.getKind()).append("]\tfor: ").
	 * append(joinPoint.getSignature())
	 * .append("\twithArgs: ").append("(").append(StringUtils.join(joinPoint.getArgs
	 * (), ",")).append(")") .append("\ttook: ");
	 * 
	 * log.info(stringBuilder.append(System.currentTimeMillis() -
	 * startTime).append(" ms.").toString()); }
	 */
	
	@Pointcut(value = "execution(* com.global.book.Controller..*(..))")
	public void forControllerLog() {}
	
	@Pointcut(value = "execution(* com.global.book.Service..*(..))")
	public void forServiceLog() {}
	
	@Pointcut(value = "execution(* com.global.book.Repository..*(..))")
	public void forRepositoryLog() {}
	
	@Pointcut(value = "forControllerLog() || forServiceLog() || forRepositoryLog()")
	public void forAllLog() {}
	
	@Before(value = "forAllLog()")
	public void beforeLog(JoinPoint joinPoint) {
		
		String methodName = joinPoint.getSignature().getName();
		log.info("Method Name =====>> {}", methodName);
		
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			log.info("Args =====>> {}", arg);
		}
	}
}
