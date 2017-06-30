package com.github.appreciated.quickstart.base.pages;

import com.github.appreciated.quickstart.base.navigation.description.SubpageDescription;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;
import com.vaadin.server.Resource;

import javax.xml.ws.Holder;

/**
 * Created by appreciated on 08.12.2016.
 */
public interface Subpage {
    Holder<Resource> resourceHolder = new Holder<>();

    default String getNavigationName() {
        SubpageDescription annotation = this.getClass().getAnnotation(SubpageDescription.class);
        if (annotation == null) {
            return null;
        }
        return annotation.name();
    }

    default Resource getNavigationIcon() {
        return resourceHolder.value;
    }

    default void setPageIcon(Resource resource) {
        resourceHolder.value = resource;
    }

    default boolean showTitle() {
        return true;
    }

    default void navigateTo() {
        QuickStartUI.getStateManager().navigateTo(this);
    }
}
