package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.navigation.interfaces.Subpage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Johannes on 07.03.2017.
 */
public class Subpages {
    List<Subpage> subpages = new ArrayList<>();

    public Subpages(Subpage... subpages) {
        this.subpages.addAll(Arrays.asList(subpages));
    }

    public Subpages add(Subpage subpage) {
        this.subpages.add(subpage);
        return this;
    }


    public List<Subpage> getSubpages() {
        return subpages;
    }
}
