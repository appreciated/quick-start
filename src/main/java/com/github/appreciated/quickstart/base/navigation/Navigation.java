package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.interfaces.Navigable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Johannes on 07.03.2017.
 */
public class Navigation {
    List<Navigable> navigables = new ArrayList<>();

    public Navigation() {

    }

    public Navigation(Navigable... navigables) {
        this.navigables.addAll(Arrays.asList(navigables));
    }

    public Navigation add(Navigable navigable) {
        this.navigables.add(navigable);
        return this;
    }

    public List<Navigable> getNavigables() {
        return navigables;
    }
}
