package com.github.appreciated.quickstart.base.pages.attributes;


import com.github.appreciated.quickstart.base.navigation.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.pages.Page;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;
import com.vaadin.ui.Component;

/**
 * Created by Johannes on 01.07.2017.
 */
public interface ManagedPage extends Page {

     default Component getViewComponent() {
        return getPageManager().getViewComponent();
    }

    default PageManager getPageManager() {
        return QuickStartDesignProvider.get().getPageManager(this);
    }
}
