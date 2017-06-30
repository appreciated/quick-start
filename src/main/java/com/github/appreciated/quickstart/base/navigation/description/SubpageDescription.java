package com.github.appreciated.quickstart.base.navigation.description;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by appreciated on 28.02.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SubpageDescription {
    public String name();
}