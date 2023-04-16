package com.aqin.custom.postProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author aqin1012 AQin.
 * @date 2022/6/7 3:23 PM
 * @Version 1.0
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("俺这是MyBeanFactoryPostProcessor.postProcessBeanFactory()W(`0`)W～");
	}
}
