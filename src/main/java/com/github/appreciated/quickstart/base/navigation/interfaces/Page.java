package com.github.appreciated.quickstart.base.navigation.interfaces;

import com.github.appreciated.quickstart.base.navigation.Description;
import com.github.appreciated.quickstart.base.navigation.WebApplicationUI;
import com.vaadin.server.Resource;
import com.vaadin.ui.Component;

import javax.xml.ws.Holder;

/**
 * Created by appreciated on 08.12.2016.
 */
public interface Page extends Component {
    Holder<Resource> resourceHolder = new Holder<>();

    default String getNavigationName() {
        Description annotation = this.getClass().getAnnotation(Description.class);
        return annotation.name();
    }

    default Resource getNavigationIcon() {
        return resourceHolder.value;
    }

    default void setPageIcon(Resource resource) {
        resourceHolder.value = resource;
    }

    default void navigateTo() {
        WebApplicationUI.getNavigation().navigateTo(this);
    }
}
