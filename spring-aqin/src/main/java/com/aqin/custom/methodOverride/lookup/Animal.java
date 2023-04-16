package com.aqin.custom.methodOverride.lookup;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/22 9:28 AM
 * @Version 1.0
 */
//@Component
public class Animal {
	public Animal() {
		System.out.println("吃点啥嘞？");
	}
}