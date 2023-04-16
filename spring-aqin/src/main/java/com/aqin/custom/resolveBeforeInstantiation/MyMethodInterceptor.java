package com.aqin.custom.resolveBeforeInstantiation;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/28 3:18 PM
 * @Version 1.0
 */
public class MyMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
			throws Throwable {
		System.out.println("MyMethodInterceptor.interceptor(): " + method + "--->");
		/** 这里会调用具体的类的方法 */
		Object invokeSuper = methodProxy.invokeSuper(o, objects);
		System.out.println("<---MyMethodInterceptor.interceptor(): " + method);
		return invokeSuper;
	}
}
