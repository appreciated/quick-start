package com.github.appreciated.quickstart.base.pages.managed;

import com.github.appreciated.quickstart.base.pages.attributes.ManagedPage;
import com.vaadin.ui.Component;

/**
 * Created by appreciated on 06.12.2016.
 */
public interface ContainedPage extends ManagedPage, Component {
    default boolean hasPadding() {
        return true;
    }

    default Component getViewComponent() {
        return getPageManager().getViewComponent();
    }

}
