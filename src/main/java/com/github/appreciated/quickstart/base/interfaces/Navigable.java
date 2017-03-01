package com.github.appreciated.quickstart.base.interfaces;

import com.github.appreciated.quickstart.base.navigation.Description;
import com.vaadin.ui.Component;

/**
 * Created by appreciated on 08.12.2016.
 */
public interface Navigable extends Component {
    default String getNavigationName() {
        Description annotation = this.getClass().getAnnotation(Description.class);
        return annotation.name();
    }

}
