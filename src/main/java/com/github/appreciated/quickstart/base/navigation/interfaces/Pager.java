package com.github.appreciated.quickstart.base.navigation.interfaces;

import java.util.List;

/**
 * Created by appreciated on 09.12.2016.
 */
public interface Pager extends Page {
    public List<Page> getPagingElements();

    public void onFinish();
}
