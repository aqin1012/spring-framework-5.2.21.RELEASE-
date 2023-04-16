package com.aqin.custom.circulate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 11/21/22 2:38 PM
 * @Version 1.0
 */
public class Test {
	public static void main(String[] args) {
 		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("circulate.xml");
		BeanA beanA = applicationContext.getBean(BeanA.class);
		System.out.println(beanA);
		BeanB beanB = beanA.getBeanB();
		System.out.println(beanB);
		BeanA beanA2 = beanB.getBeanA();
		System.out.println(beanA2);
	}

}
