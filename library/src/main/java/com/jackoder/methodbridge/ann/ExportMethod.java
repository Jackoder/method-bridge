package com.jackoder.methodbridge.ann;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ExportMethod {

    String[] name() default "";
}
