package com.github.appreciated.quickstart.base.authentication.registration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by appreciated on 05.03.2017.
 */
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    Class<? extends Object> type() default String.class;

    String caption();

    boolean required() default true;
}