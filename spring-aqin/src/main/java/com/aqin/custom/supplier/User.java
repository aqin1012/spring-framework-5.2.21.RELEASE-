package com.aqin.custom.supplier;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/29 5:21 PM
 * @Version 1.0
 */
public class User {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User() {
		System.out.println("无参构造 -> User()");
	}

	public User(String name) {
		this.name = name;
	}

}
