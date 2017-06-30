package com.github.appreciated.quickstart.base.components;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;

/**
 * Created by Johannes on 30.06.2017.
 */
public class Helper {
    public static void prepareContainerForComponent(Layout container, Component component) {
        if (component.getWidthUnits() == Sizeable.Unit.PERCENTAGE) {
            container.setWidth(100, Sizeable.Unit.PERCENTAGE);
        } else {
            container.setWidthUndefined();
        }
        if (component.getHeightUnits() == Sizeable.Unit.PERCENTAGE) {
            container.setHeight(100, Sizeable.Unit.PERCENTAGE);
        } else {
            container.setHeightUndefined();
        }
    }

    public static boolean requiresPercentageHeight(Component component) {
        return  component.getWidthUnits() == Sizeable.Unit.PERCENTAGE;
    }
}
