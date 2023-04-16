package com.aqin.custom.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 11/11/22 9:50 AM
 * @Version 1.0
 */
public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("populateBean.xml");
		MyAnnotationController bean = applicationContext.getBean(MyAnnotationController.class);
		bean.testMyAnnotation();
	}
}
