package org.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author aqin1012 AQin.
 * @Date 11/10/22 1:08 PM
 * @Version 1.0
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AqinAutowired {
	/**
	 * Declares whether the annotated dependency is required.
	 * <p>Defaults to {@code true}.
	 */
	boolean required() default true;
}
