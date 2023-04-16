package com.aqin.custom.factoryMethod;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/30 1:14 PM
 * @Version 1.0
 */
//@Component
public class Student {
	public Student() {
		System.out.println("Student()~~~");
	}

	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct -> init()");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("@PreDestroy -> destroy()");
	}

}
