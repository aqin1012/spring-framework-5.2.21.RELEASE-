package com.aqin.custom.resolveBeforeInstantiation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/28 3:43 PM
 * @Version 1.0
 */
public class test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resolveBeforeInstantiation.xml");
		BeanInstantiation beanInstantiation = (BeanInstantiation) applicationContext.getBean("beanInstantiation");
		beanInstantiation.smile();
	}
}
