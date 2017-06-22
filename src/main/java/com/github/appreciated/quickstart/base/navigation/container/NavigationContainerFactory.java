package com.github.appreciated.quickstart.base.navigation.container;

import com.github.appreciated.quickstart.base.navigation.WebApplicationUI;
import com.vaadin.ui.Layout;

/**
 * Created by Johannes on 22.06.2017.
 */
public class NavigationContainerFactory {
    public static Layout getNavigationContainer() {
        return WebApplicationUI.get().getNavigationContainer();
    }
}
