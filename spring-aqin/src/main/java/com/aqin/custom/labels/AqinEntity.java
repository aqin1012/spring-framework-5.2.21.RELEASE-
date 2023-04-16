package com.aqin.custom.labels;

import org.springframework.stereotype.Component;

/**
 * @author aqin1012 AQin.
 * @date 2022/4/24 5:45 PM
 * @Version 1.0
 */
@Component
public class AqinEntity {
	String id;
	String name;
	String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}