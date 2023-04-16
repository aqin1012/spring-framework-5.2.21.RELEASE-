package com.aqin.custom.propertyEditor;

import org.springframework.stereotype.Component;

/**
 * @author aqin1012 AQin.
 * @date 2022/5/27 1:35 PM
 * @Version 1.0
 */
//@Component
public class Address {
	private String province;
	private String city;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
