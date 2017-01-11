package com.github.appreciated.quickstart.base.navigation;

import com.github.appreciated.quickstart.base.interfaces.Navigable;
import com.vaadin.ui.Button;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;

/**
 * Created by appreciated on 01.01.2017.
 */
public abstract class NavigationDefinition {


    public abstract String getTitle();

    public abstract List<AbstractMap.SimpleEntry<Button, Navigable>> getButtons();

    public abstract  List<AbstractMap.SimpleEntry<String, Boolean>> getConfiguration();

    public AbstractMap.SimpleEntry<Button, Navigable> getButton(Button button, Navigable navigable) {
        return new AbstractMap.SimpleEntry<Button, Navigable>(button, navigable);
    }

    public List<AbstractMap.SimpleEntry<Button, Navigable>> getButtons(AbstractMap.SimpleEntry<Button, Navigable>... buttons) {
        return Arrays.asList(buttons);
    }

    public AbstractMap.SimpleEntry<String, Boolean> getConfiguration(String key, boolean value) {
        return new AbstractMap.SimpleEntry<String, Boolean>(key, new Boolean(value));
    }

    public List<AbstractMap.SimpleEntry<String, Boolean>> getConfigurations(AbstractMap.SimpleEntry<String, Boolean>... properties) {
        return Arrays.asList(properties);
    }

    public abstract Class<? extends Navigable> getStartNavigable();
}
