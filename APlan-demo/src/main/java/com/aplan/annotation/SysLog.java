
package com.aplan.annotation;

import java.lang.annotation.*;

/**
 * 日志记录注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
