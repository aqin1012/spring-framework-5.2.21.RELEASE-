package com.aqin.custom.resolveBeforeInstantiation;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/28 2:01 PM
 * @Version 1.0
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		/** 创建代理 */
		if (beanClass == BeanInstantiation.class) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(beanClass);
			enhancer.setCallback(new MyMethodInterceptor());
			BeanInstantiation beanInstantiation = (BeanInstantiation) enhancer.create();
			System.out.println("创建代理对象：" + beanInstantiation);
			return beanInstantiation;
		}
		return null;
	}

	@Override
	public boolean  postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		System.out.println("=== postProcessAfterInstantiation ===");
		return false;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("=== postProcessBeforeInitialization ===");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("=== postProcessAfterInitialization ===");
		return bean;
	}

	/**
	 * 对当前属性值的相关处理工作
	 *
	 * @param pvs      the property values that the factory is about to apply (never {@code null})
	 * @param bean     the bean instance created, but whose properties have not yet been set
	 * @param beanName the name of the bean
	 * @return
	 * @throws BeansException
	 */
	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		System.out.println("=== postProcessProperties ===");
		return pvs;
	}
}
