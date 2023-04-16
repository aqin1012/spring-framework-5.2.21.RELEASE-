package com.aqin.custom.propertyEditor;

import org.springframework.beans.factory.FactoryBean;

import java.util.List;

/**
 * @author aqin1012 AQin.
 * @date 2022/4/24 5:45 PM
 * @Version 1.0
 */
//@Component
public class AqinEntity{
	private int id;
	private List<String> name;
	private Address address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}