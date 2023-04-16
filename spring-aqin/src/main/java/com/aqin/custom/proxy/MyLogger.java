package com.aqin.custom.proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 11/23/22 1:19 PM
 * @Version 1.0
 */
@Aspect
public class MyLogger {

	@Pointcut("execution(* *(..))")
	public void myPointCut() {

	}

	@Around("myPointCut()")
	public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
		Signature signature = proceedingJoinPoint.getSignature();
		Object[] args = proceedingJoinPoint.getArgs();
		Object proceed = null;
		try {
			System.out.println("@Around-Before-" + signature.getName() + " args-" + Arrays.asList(args));
			proceed = proceedingJoinPoint.proceed(args);
			System.out.println("@Around-After-" + proceed.toString());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return proceed;
	}

	@Before(value = "myPointCut()")
	public void beforeMethod(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		System.out.println("@Before-" + signature.getName());
	}

	@After(value = "myPointCut()")
	public void afterMethod(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		System.out.println("@After-" + signature.getName());
	}

	@AfterReturning(value = "myPointCut()", returning = "result")
	public void afterReturningMethod(JoinPoint joinPoint, Object result) {
		Signature signature = joinPoint.getSignature();
		System.out.println("@AfterReturning-" + signature.getName() + " Returning-" + result);
	}

	@AfterThrowing(value = "myPointCut()", throwing = "e")
	public void afterAfterThrowingMethod(JoinPoint joinPoint, Exception e) {
		Signature signature = joinPoint.getSignature();
		System.out.println("@AfterThrowing-" + signature.getName() + " Exception-" + e.getMessage());
	}
}
