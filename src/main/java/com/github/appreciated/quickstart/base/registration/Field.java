package com.github.appreciated.quickstart.base.registration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Johannes on 05.03.2017.
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    Class<? extends Object> type() default String.class;

    String caption();

    boolean required() default true;
}