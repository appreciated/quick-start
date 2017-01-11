package com.github.appreciated.quickstart.base.interfaces;

import java.util.List;

/**
 * Created by appreciated on 09.12.2016.
 */
public interface PagerNavigable extends Navigable {
    public List<Navigable> getPagingElements();

    public void onFinish();
}
