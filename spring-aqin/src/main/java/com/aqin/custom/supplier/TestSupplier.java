package com.aqin.custom.supplier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/29 5:52 PM
 * @Version 1.0
 */
public class TestSupplier {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("supplier.xml");
		User user = applicationContext.getBean(User.class);
//		applicationContext.close();
		System.out.println(user.getName());
	}
}
