package com.aqin.custom.collectionsPopulateOrder;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 1/19/23 7:55 AM
 * @Version 1.0
 */
@Component
@SuppressWarnings("serial")
public class MyEntity implements Serializable {
	private int integer;
	private AqinEntity aqinEntity;
	private List<AqinEntity> aqinEntityList;
	private List<Integer> integerList;

	public void getMyEntity() {
		System.out.println(integer);
		System.out.println(aqinEntity);
		System.out.println(Arrays.asList(aqinEntityList));
		System.out.println(Arrays.asList(integerList));
	}

	@Override
	public String toString() {
		return "MyEntity{" +
				"integer=" + integer +
				", aqinEntity=" + aqinEntity +
				", aqinEntityList=" + aqinEntityList +
				", integerList=" + integerList +
				'}';
	}

	public int getInteger() {
		return integer;
	}

	public void setInteger(int integer) {
		this.integer = integer;
	}

	public AqinEntity getAqinEntity() {
		return aqinEntity;
	}

	public void setAqinEntity(AqinEntity aqinEntity) {
		this.aqinEntity = aqinEntity;
	}

	public List<AqinEntity> getAqinEntityList() {
		return aqinEntityList;
	}

	public void setAqinEntityList(List<AqinEntity> aqinEntityList) {
		this.aqinEntityList = aqinEntityList;
	}

	public List<Integer> getIntegerList() {
		return integerList;
	}

	public void setIntegerList(List<Integer> integerList) {
		this.integerList = integerList;
	}
}
