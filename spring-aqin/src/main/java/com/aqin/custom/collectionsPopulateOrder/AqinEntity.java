package com.aqin.custom.collectionsPopulateOrder;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author aqin1012 AQin.
 * @date 2022/4/24 5:45 PM
 * @Version 1.0
// */
//@Component
@SuppressWarnings("serial")
public class AqinEntity implements Serializable {
	Integer id;
	String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}