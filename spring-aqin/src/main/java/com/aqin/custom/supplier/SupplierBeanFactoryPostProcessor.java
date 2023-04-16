package com.aqin.custom.supplier;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/29 5:26 PM
 * @Version 1.0
 */
public class SupplierBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user");
		GenericBeanDefinition genericBeanDefinition = (GenericBeanDefinition) beanDefinition;
		genericBeanDefinition.setInstanceSupplier(CreateSupplier::createUser);
		genericBeanDefinition.setBeanClass(User.class);
	}
}
