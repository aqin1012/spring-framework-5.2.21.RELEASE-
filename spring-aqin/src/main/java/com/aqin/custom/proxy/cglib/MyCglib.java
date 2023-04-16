package com.aqin.custom.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 12/28/22 5:36 PM
 * @Version 1.0
 */
public class MyCglib implements MethodInterceptor {
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		Object o1 = methodProxy.invokeSuper(o, objects);
		return o1;
	}
}
