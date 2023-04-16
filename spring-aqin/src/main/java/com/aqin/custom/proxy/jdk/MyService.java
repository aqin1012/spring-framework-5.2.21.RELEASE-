package com.aqin.custom.proxy.jdk;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 12/25/22 4:26 PM
 * @Version 1.0
 */
public class MyService implements IService {
	@Override
	public void A() {
		System.out.println("This is A().");
	}

	@Override
	public String B(int i) {
		return "This is B()." + i;
	}
}
