package com.aqin.custom.selfBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 11/15/22 11:14 AM
 * @Version 1.0
 */
public class Test {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext2.xml");
		MyBean bean = applicationContext.getBean(MyBean.class);
		System.out.println(bean.getName());
		MyNewBean myNewBean = new MyNewBean();
		myNewBean.setName("张三");
		myNewBean.setAge(99);
	}
}
