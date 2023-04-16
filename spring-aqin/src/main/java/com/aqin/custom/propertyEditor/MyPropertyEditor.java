package com.aqin.custom.propertyEditor;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author aqin1012 AQin.
 * @date 2022/5/30 10:55 AM
 * @Version 1.0
 */
public class MyPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			String[] addrs = text.split("_");
			Address address = new Address();
			address.setProvince(addrs[0]);
			address.setCity(addrs[1]);
			this.setValue(address);
			return;
		}
		throw new IllegalArgumentException("MyPropertyEditor: " + text + " could not convert to the target type!");
	}
}
