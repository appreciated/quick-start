package com.github.appreciated.quickstart.base.navigation.actions;

import com.vaadin.ui.Component;

/**
 * Created by Johannes on 09.03.2017.
 */
public abstract class CustomAction extends Action {
    private Component component;
    private Component mobileComponent;
    private Component desktopComponent;

    public CustomAction(Component component) {
        super(null, null);
        this.component = component;
    }

    public Component getMobileComponent() {
        return mobileComponent;
    }


    public abstract boolean hasMobileComponent();

    public Component getDesktopComponent() {
        return desktopComponent;
    }
}
