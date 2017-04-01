package com.github.appreciated.quickstart.base.navigation.actions;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;

/**
 * Created by appreciated on 09.03.2017.
 */
public class CustomAction extends Action {

    private Alignment alignment;

    private Component mobileComponent;
    private Component desktopComponent;
    private boolean insertLeft;

    public CustomAction(Component component) {
        super(null, null);
        this.desktopComponent = component;
        alignment = Alignment.TOP_LEFT;
        insertLeft = false;
    }

    public Component getMobileComponent() {
        return mobileComponent;
    }

    /**
     * Override if you Design if the component can be added to it on the mobile design
     *
     * @return
     */
    public boolean isMobileCompliant() {
        return false;
    }

    public boolean insertLeft() {
        return insertLeft;
    }

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

    public CustomAction withInsertLeft(boolean insertLeft) {
        this.insertLeft = insertLeft;
        return this;
    }

    public void setMobileComponent(Component mobileComponent) {
        this.mobileComponent = mobileComponent;
    }
}
