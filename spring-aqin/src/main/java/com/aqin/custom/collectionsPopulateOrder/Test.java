package com.aqin.custom.collectionsPopulateOrder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 1/19/23 8:01 AM
 * @Version 1.0
 */
public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("collection.xml");
		MyEntity bean = applicationContext.getBean(MyEntity.class);
		System.out.println(bean.toString());
		System.out.println("·········俺是手动分割线·········");
		bean.setInteger(1);
		AqinEntity aqinEntity = new AqinEntity();
		aqinEntity.setId(9);
		aqinEntity.setName("张3");
		bean.setAqinEntity(aqinEntity);
		AqinEntity aqinEntity2 = new AqinEntity();
		aqinEntity2.setId(99);
		aqinEntity2.setName("李4");
		AqinEntity aqinEntity3 = new AqinEntity();
		aqinEntity3.setId(999);
		aqinEntity3.setName("王5");
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		bean.setIntegerList(list);
		List<AqinEntity> aqinEntityList = new ArrayList<>();
		aqinEntityList.add(aqinEntity);
		aqinEntityList.add(aqinEntity2);
		aqinEntityList.add(aqinEntity3);
		bean.setAqinEntityList(aqinEntityList);

		System.out.println(bean.toString());
		System.out.println("·········俺是手动分割线·········");

	}
}