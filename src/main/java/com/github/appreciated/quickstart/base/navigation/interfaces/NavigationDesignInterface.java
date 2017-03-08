package com.github.appreciated.quickstart.base.navigation.interfaces;

import com.github.appreciated.quickstart.base.navigation.WebAppDescription;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;

import java.util.AbstractMap;
import java.util.stream.Stream;

/**
 * Created by appreciated on 10.12.2016.
 */
public interface NavigationDesignInterface extends Component {

    default void initWithConfiguration(Stream<AbstractMap.SimpleEntry<String, Boolean>> configurations) {}

    void initNavigationElements(Stream<Subpage> pages);

    void initUserFunctionality(WebAppDescription description);

    void initWithTitle(String title);

    AbstractOrderedLayout getHolder();

    void disableLogout();

    void setCurrentContainerLabel(String label);

    void setCurrentActions(HasContextButtons actions);

    void setCurrentSearchNavigable(HasSearch navigable);
}