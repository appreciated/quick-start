package com.github.appreciated.quickstart.base.navigation.theme;

import com.github.appreciated.quickstart.base.authentication.login.AccessControl;
import com.github.appreciated.quickstart.base.authentication.registration.RegistrationControl;
import com.github.appreciated.quickstart.base.navigation.description.WebAppDescription;
import com.github.appreciated.quickstart.base.pages.Page;
import com.github.appreciated.quickstart.base.pages.attributes.HasContextActions;
import com.github.appreciated.quickstart.base.pages.attributes.HasSearch;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;

import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by appreciated on 10.12.2016.
 */
public interface PageHolder extends Component {

    default void initWithConfiguration(Stream<AbstractMap.SimpleEntry<String, Boolean>> configurations) {
    }

    void initNavigationElements(Stream<Page> pages);

    void initUserFunctionality(WebAppDescription description);

    void initCustomMenuElements(List<Component> components);

    void initWithTitle(String title);

    AbstractOrderedLayout getHolder();

    void disableLogout();

    void setCurrentContainerLabel(String label);

    void setCurrentActions(HasContextActions actions);

    void allowPercentagePageHeight(boolean allow);

    default void setPageTitleVisibility(boolean hide) {
    }

    void setCurrentSearchNavigable(HasSearch navigable);

    void initWithAccessControl(AccessControl accessControl);

    void initRegistrationControl(RegistrationControl registrationControl);

    Layout getContainerView();

    void addPage(Page page);
}
