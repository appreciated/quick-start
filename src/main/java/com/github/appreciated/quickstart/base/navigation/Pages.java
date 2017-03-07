package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.navigation.interfaces.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Johannes on 07.03.2017.
 */
public class Pages {
    List<Page> pages = new ArrayList<>();

    public Pages() {

    }

    public Pages(Page... pages) {
        this.pages.addAll(Arrays.asList(pages));
    }

    public Pages add(Page page) {
        this.pages.add(page);
        return this;
    }


    public List<Page> getPages() {
        return pages;
    }
}
