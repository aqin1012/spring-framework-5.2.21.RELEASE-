package com.aqin.custom.methodOverride.test;

import com.aqin.custom.methodOverride.lookup.AnimalAction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/22 10:29 AM
 * @Version 1.0
 */
public class TestMethodOverride {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("MethodOverride.xml");
		AnimalAction animalActionB = (AnimalAction) applicationContext.getBean("animalAction_B");
		animalActionB.getAnimal();
		AnimalAction animalActionB_2 = (AnimalAction) applicationContext.getBean("animalAction_B");
		animalActionB_2.getAnimal();
	}
}
