package com.aqin.custom.selfBean;

import org.springframework.context.annotation.Bean;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 11/15/22 11:11 AM
 * @Version 1.0
 */
@Bean
public class MyNewBean {
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
}
