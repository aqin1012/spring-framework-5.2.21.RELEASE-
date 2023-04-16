package com.aqin.custom.proxy.cglib;


/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 12/25/22 4:26 PM
 * @Version 1.0
 */
public class MyService{
	public void A() {
		System.out.println("This is A().");
	}

	public String B(int i) {
		return "This is B()." + i;
	}
}
