package com.github.appreciated.quickstart.base.pages;

import com.github.appreciated.quickstart.base.navigation.description.SubpageDescription;
import com.github.appreciated.quickstart.base.navigation.theme.QuickStartDesignProvider;
import com.github.appreciated.quickstart.base.ui.QuickStartUI;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import javax.xml.ws.Holder;

/**
 * Created by appreciated on 08.12.2016.
 */
public interface Page extends View {
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
        UI.getCurrent().getNavigator().navigateTo(getNavigationName());
    }
    default void onSubpageLoaded(){}
    default void onSubpageFinish(){}

    default void onUpdate() {
        //QuickStartDesignProvider.get().onUpdate();
    }
}
