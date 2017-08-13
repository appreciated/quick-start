package com.github.appreciated.quickstart.base.navigation.description;

import com.github.appreciated.quickstart.base.pages.Page;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by appreciated on 07.03.2017.
 */
public class Pages {
    LinkedList<Page> pages = new LinkedList<>();

    public Pages(Page... pages) {
        this.pages.addAll(Arrays.asList(pages));
    }

    public Pages add(Page page) {
        this.pages.add(page);
        return this;
    }


    public LinkedList<Page> getPages() {
        return pages;
    }

    public Page first() {
        return pages.get(0);
    }
}
