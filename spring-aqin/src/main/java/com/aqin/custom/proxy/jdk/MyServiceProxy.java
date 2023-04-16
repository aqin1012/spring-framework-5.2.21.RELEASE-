package com.aqin.custom.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 12/25/22 4:28 PM
 * @Version 1.0
 */
public class MyServiceProxy {
	public static IService getProxy(final IService iService) {
		/** 获取类加载器 */
		ClassLoader classLoader = iService.getClass().getClassLoader();
		/** 获取接口集合 */
		Class<?>[] interfaces = iService.getClass().getInterfaces();
		InvocationHandler invocationHandler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object invoke = method.invoke(iService, args);
				return invoke;
			}
		};
		Object proxyInstance = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
		return (IService) proxyInstance;
	}
}
