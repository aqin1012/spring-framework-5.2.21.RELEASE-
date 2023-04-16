package com.aqin.custom.factoryMethod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/30 1:47 PM
 * @Version 1.0
 */
public class TestFactoryMethod {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("factoryMethod.xml");
		Student bean = applicationContext.getBean("student", Student.class);
		System.out.println(bean.getName());
		System.out.println("-------------------------------");
		Student bean2 = applicationContext.getBean("student2", Student.class);
		System.out.println(bean2.getName());
	}
}
