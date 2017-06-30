package com.github.appreciated.quickstart.base.navigation.interfaces.theme;

import com.github.appreciated.quickstart.base.navigation.interfaces.base.ComponentSubpage;

/**
 * Created by appreciated on 22.06.2017.
 */
public interface PagerImplementation extends ComponentSubpage{
    public void next();
    public void last();
    public void onFinish();
}
