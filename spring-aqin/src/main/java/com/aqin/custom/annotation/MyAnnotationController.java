package com.aqin.custom.annotation;


/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 11/10/22 5:07 PM
 * @Version 1.0
 */
public class MyAnnotationController {

	@AqinAutowired
	private MyAnnotationService myAnnotationService;

	public void testMyAnnotation(){
		myAnnotationService.test();
	}

}
