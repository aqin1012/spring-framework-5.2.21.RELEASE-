package com.aqin.custom.factoryMethod;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 2022/8/30 1:18 PM
 * @Version 1.0
 */
public class StudentInstanceFactory {
	public Student getStudent(String name) {
		Student student = new Student();
		student.setName(name);
		student.setAge(88);
		return student;
	}
}
