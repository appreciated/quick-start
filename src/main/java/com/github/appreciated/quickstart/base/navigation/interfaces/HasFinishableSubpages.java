package com.github.appreciated.quickstart.base.navigation.interfaces;

import java.util.List;

/**
 * Created by Johannes on 19.03.2017.
 */
public abstract interface HasFinishableSubpages {
    List<Finishable> getPagingElements();
}
