package com.github.appreciated.quickstart.base.navigation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Johannes on 28.02.2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
    public String name();
}
