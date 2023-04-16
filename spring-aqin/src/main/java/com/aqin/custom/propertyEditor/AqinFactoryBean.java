package com.aqin.custom.propertyEditor;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author aqin1012 AQin.
 * @date 2022/4/24 5:45 PM
 * @Version 1.0
 */
@Component
public class AqinFactoryBean implements FactoryBean<Address>{
	@Override
	public Address getObject() {
		return new Address();
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}
}