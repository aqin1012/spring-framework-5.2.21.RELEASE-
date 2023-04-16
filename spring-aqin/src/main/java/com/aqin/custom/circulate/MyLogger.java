package com.aqin.custom.circulate;

import org.aspectj.lang.annotation.Before;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 11/23/22 1:19 PM
 * @Version 1.0
 */
public class MyLogger {
	public void beforeMethod() {
		System.out.println("beforeMethod");
	}
	public void afterMethod() {
		System.out.println("afterMethod");
	}
}
