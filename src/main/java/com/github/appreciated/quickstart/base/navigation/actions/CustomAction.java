package com.github.appreciated.quickstart.base.navigation.actions;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;

/**
 * Created by appreciated on 09.03.2017.
 */
public abstract class CustomAction extends Action {

    private Alignment alignment;

    private Component mobileComponent;
    private Component desktopComponent;

    public CustomAction(Component component) {
        super(null, null);
        this.desktopComponent = component;
    }

    public Component getMobileComponent() {
        return mobileComponent;
    }

    public abstract boolean isMobileCompliant();

    public Component getDesktopComponent() {
        return desktopComponent;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public Action withAlignment(Alignment alignment) {
        this.alignment = alignment;
        return this;
    }
}
