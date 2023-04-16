package com.aqin.custom.propertyEditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @author aqin1012 AQin.
 * @date 2022/5/30 11:10 AM
 * @Version 1.0
 */
public class MyPropertyEditorRegistrar implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(Address.class, new MyPropertyEditor());
	}

}
