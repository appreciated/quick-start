package com.github.appreciated.quickstart.base.navigation.interfaces.theme;

import com.github.appreciated.quickstart.base.navigation.interfaces.base.Subpage;
import com.vaadin.ui.Component;

/**
 * Created by appreciated on 15.12.2016.
 */
public interface QuickStartLoginView extends Component, Subpage, QuickStartLogin {
    void initTitle(String title);
}
