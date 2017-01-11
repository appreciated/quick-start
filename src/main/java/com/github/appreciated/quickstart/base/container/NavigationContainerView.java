package com.github.appreciated.quickstart.base.container;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * Created by appreciated on 11.12.2016.
 */
public class NavigationContainerView extends NavigationContainerDesign {

    public Label getContainerLabel() {
        return container_title;
    }
    public HorizontalLayout getLabelHolder() {
        return labelHolder;
    }
    public HorizontalLayout getContentHolder() {
        return content;
    }

}
