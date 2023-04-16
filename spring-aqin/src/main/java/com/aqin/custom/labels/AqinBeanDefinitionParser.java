package com.aqin.custom.labels;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/5/12 3:27 PM
 * @Version 1.0
 */
public class AqinBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return AqinEntity.class;
	}

	/**
	 * 此处缺少某个属性并不会导致程序报错，但是该属性会为null
	 *
	 * @param element the XML element being parsed
	 * @param builder used to define the {@code BeanDefinition}
	 */
	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");
		String address = element.getAttribute("address");

		if (StringUtils.hasText(id)) {
			builder.addPropertyValue("id", id);
		}

		if (StringUtils.hasText(name)) {
			builder.addPropertyValue("name", name);
		}

		if (StringUtils.hasText(address)) {
			builder.addPropertyValue("address", address);
		}
	}
}
