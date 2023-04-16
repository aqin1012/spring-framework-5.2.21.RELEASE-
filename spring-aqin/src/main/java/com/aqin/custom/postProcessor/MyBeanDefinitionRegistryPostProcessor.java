package com.aqin.custom.postProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author aqin1012 AQin.
 * @date 2022/6/6 11:05 AM
 * @Version 1.0
 */
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("俺这是MyRegistryPostProcessor.postProcessBeanFactory()( ´▽｀)～");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Person.class);
//		beanDefinitionBuilder.addPropertyValue("name", "aqin");
//		registry.registerBeanDefinition("person", beanDefinitionBuilder.getBeanDefinition());
		System.out.println("俺这是MyRegistryPostProcessor.postProcessBeanDefinitionRegistry()(*≧ω≦)～");
	}
}
