package com.aqin;

import com.aqin.custom.annotation.MyAnnotationController;
import com.aqin.custom.propertyEditor.Address;
import com.aqin.custom.propertyEditor.AqinEntity;
import com.aqin.custom.propertyEditor.AqinFactoryBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author aqin1012 AQin.
 * @date 2022/4/24 5:44 PM
 * @Version 1.0
 */
public class AqinApplication {
	public static void main(String[] args) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
//		MyAnnotationController myAnnotationController = context.getBean("MyAnnotation", MyAnnotationController.class);
//		myAnnotationController.testMyAnnotation();

//		AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
//		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//		beanDefinition.setBeanClass(MyAnnotationController.class);
//		beanDefinition.setScope("singleton");
//		configApplicationContext.registerBeanDefinition("myAnnotationController", beanDefinition);
//		MyAnnotationController controller = (MyAnnotationController) configApplicationContext.getBean("myAnnotationController");
//		System.out.println(controller);
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
//		ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
//		System.out.println(context.getBean(AqinFactoryBean.class));
		AqinEntity aqin = context.getBean(AqinEntity.class);
//		Address object = null;
//		try {
//			object = aqin.getObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(object);
		System.out.println(aqin.getAddress().getProvince());
		System.out.println(aqin.getAddress().getCity());
	}
}