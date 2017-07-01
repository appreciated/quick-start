package com.github.appreciated.quickstart.base.navigation.description;

import com.github.appreciated.quickstart.base.pages.Page;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by appreciated on 07.03.2017.
 */
public class Subpages {
    LinkedList<Page> pages = new LinkedList<>();

    public Subpages(Page... pages) {
        this.pages.addAll(Arrays.asList(pages));
    }

    public Subpages add(Page page) {
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
