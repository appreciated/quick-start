package com.github.appreciated.quickstart.base.navigation.container;

import com.github.appreciated.quickstart.base.navigation.interfaces.HasSubpages;
import com.github.appreciated.quickstart.base.navigation.interfaces.Subpage;

/**
 * Created by Johannes on 22.06.2017.
 */
public interface Pager extends Subpage, HasSubpages {
    public void next();
    public void last();
    public void onFinish();
}
