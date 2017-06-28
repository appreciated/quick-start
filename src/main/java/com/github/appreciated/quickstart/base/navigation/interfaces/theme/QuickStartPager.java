package com.github.appreciated.quickstart.base.navigation.interfaces.theme;

import com.github.appreciated.quickstart.base.navigation.interfaces.base.ComponentSubpage;

/**
 * Created by Johannes on 22.06.2017.
 */
public interface QuickStartPager extends ComponentSubpage{
    public void next();
    public void last();
    public void onFinish();
}
