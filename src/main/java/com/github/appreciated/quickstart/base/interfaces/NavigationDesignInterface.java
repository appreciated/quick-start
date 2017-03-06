package com.github.appreciated.quickstart.base.interfaces;

import com.github.appreciated.quickstart.base.navigation.WebAppDescription;
import com.github.appreciated.quickstart.base.navigation.WebApplicationUI;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;

import java.util.AbstractMap;
import java.util.stream.Stream;

/**
 * Created by appreciated on 10.12.2016.
 */
public interface NavigationDesignInterface extends Component {

    public default WebAppDescription getWebAppDescription() {
        return WebApplicationUI.get().getWebsiteDescription();
    }



    public default void initWithConfiguration(Stream<AbstractMap.SimpleEntry<String, Boolean>> configurations) {}

    public void initNavigationElements(Stream<Navigable> navigables);

    public void initUserFunctionality(WebAppDescription description);

    void initWithTitle(String title);

    public AbstractOrderedLayout getHolder();

    public void disableLogout();

    public void setCurrentContainerLabel(String label);

    public void setCurrentActions(ContextNavigable actions);

    public void setCurrentSearchNavigable(SearchNavigable navigable);


}
