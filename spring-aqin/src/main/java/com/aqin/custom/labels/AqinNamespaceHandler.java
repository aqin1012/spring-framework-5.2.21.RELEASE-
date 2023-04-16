package com.aqin.custom.labels;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author aqin1012
 */
public class AqinNamespaceHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("aqin", new AqinBeanDefinitionParser());
	}

}
