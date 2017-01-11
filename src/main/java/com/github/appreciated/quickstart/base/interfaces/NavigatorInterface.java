package com.github.appreciated.quickstart.base.interfaces;

import com.github.appreciated.quickstart.base.navigation.Navigation;
import com.github.appreciated.quickstart.base.navigation.NavigationDefinition;
import com.vaadin.ui.Button;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;

/**
 * Created by appreciated on 10.12.2016.
 */
public interface NavigatorInterface {

    public Navigation getNavigation();

    public abstract NavigationDefinition getConfiguration();

    public void disableLogout();

}
