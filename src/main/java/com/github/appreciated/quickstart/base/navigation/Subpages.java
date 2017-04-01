package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.navigation.interfaces.Subpage;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by appreciated on 07.03.2017.
 */
public class Subpages {
    LinkedList<Subpage> subpages = new LinkedList<>();

    public Subpages(Subpage... subpages) {
        this.subpages.addAll(Arrays.asList(subpages));
    }

    public Subpages add(Subpage subpage) {
        this.subpages.add(subpage);
        return this;
    }


    public LinkedList<Subpage> getSubpages() {
        return subpages;
    }

    public Subpage first() {
        return subpages.get(0);
    }
}
