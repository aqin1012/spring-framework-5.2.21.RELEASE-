package com.aqin.custom.postProcessor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author aqin1012 AQin.
 * @date 2022/6/6 11:14 AM
 * @Version 1.0
 */
public class MyApplicationContext extends ClassPathXmlApplicationContext {

	public MyApplicationContext(String... s) {
		super(s);
	}

//	@Override
//	protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
//		super.addBeanFactoryPostProcessor(new MyBeanFactoryPostProcessor());
//		super.addBeanFactoryPostProcessor(new MyBeanDefinitionRegistryPostProcessor());
//	}
}
