package com.github.appreciated.quickstart.base.navigation.interfaces.theme;

import com.github.appreciated.quickstart.base.navigation.interfaces.base.Subpage;
import com.vaadin.ui.Component;

/**
 * Created by appreciated on 15.12.2016.
 */
public interface LoginImplementationView extends Component, Subpage, LoginImplementation {
    void initTitle(String title);
}
